package com.api.cadastro.service.impl.v1;


import com.api.cadastro.config.LoggerConfig;
import com.api.cadastro.domain.model.v1.ApiException;
import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.domain.repository.v1.CadastroRedisRepository;
import com.api.cadastro.domain.repository.v1.CadastroRepository;
import com.api.cadastro.dto.CadastroDto;
import com.api.cadastro.dto.ResponseSuccess;
import com.api.cadastro.service.v1.CadastroService;
import com.api.cadastro.utils.DataValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CadastroServiceImpl implements CadastroService {

    final CadastroRepository cadastroRepository;
    final CadastroRedisRepository cadastroRedisRepository;
    final DataValidationUtil dataValidationUtil;

    public CadastroServiceImpl(CadastroRepository cadastroRepository, CadastroRedisRepository cadastroRedisRepository, DataValidationUtil dataValidationUtil) {
        this.cadastroRepository = cadastroRepository;
        this.cadastroRedisRepository = cadastroRedisRepository;
        this.dataValidationUtil = dataValidationUtil;
    }

    private final Integer STATUS_CODE_CREATED = 201;
    private final Integer STATUS_CODE_OK = 200;
    private final Integer STATUS_CODE_NOT_FOUND = 404;
    private final Integer STATUS_CODE_BAD_REQUEST = 400;
    private final Integer STATUS_CODE_CONFLICT = 409;

    private final String ENDPOINT_DEFAULT_CONTROLLER_V1 = "/cadastro/v1";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String KEY_NAME = "CADASTRO-CLIENTES";

    //    name email telephone address nascimento sex cpf
    @Override
    public ResponseEntity<?> save(CadastroModel cadastroModel) {
        System.out.println(cadastroModel.getNascimento());
        if (dataValidationUtil.validationDatasToSave(cadastroModel)){
            LoggerConfig.setErrorLog(
                    ENDPOINT_DEFAULT_CONTROLLER_V1+"/save",
                    STATUS_CODE_CONFLICT,
                    "Campos obrigatorios ficaram vazios.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            throw new ApiException(STATUS_CODE_CONFLICT,"Campos obrigatórios ficaram vazios.");
        }else {
            CadastroModel cadastroModel1 = cadastroRepository.findByEmail(cadastroModel.getEmail());
            if (cadastroModel1 == null){
                cadastroRepository.save(cadastroModel);
                Map<Object, Object> redisMap = new HashMap<>();

                redisMap.put(cadastroModel.getCpf(), objectMapper.convertValue(cadastroModel, Map.class));
                cadastroRedisRepository.insertHashKey(KEY_NAME, redisMap);

                ResponseSuccess responseSuccess = new ResponseSuccess(STATUS_CODE_OK,"Cadastro salvo com sucesso");
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/save",
                        STATUS_CODE_CREATED,
                        "Cadastro salvo com sucesso",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            }else{
                LoggerConfig.setErrorLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/save",
                        STATUS_CODE_NOT_FOUND,
                        "Dados nao Cadastro já existente.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                throw new ApiException(STATUS_CODE_BAD_REQUEST, "Usuario ja esta salvo.");
            }
        }
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<CadastroModel> cadastroModels =  cadastroRepository.findAll();
        if (cadastroModels.size() > 0){
            LoggerConfig.setAnalyticsLog(
                    "/cadastro/v1/getAll",
                    STATUS_CODE_OK,
                    "Todos os cadastro foram puxado com sucesso",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());

            Map<Object, Object> redisMap = new HashMap<>();
            for (CadastroModel cadastroModel : cadastroModels){
                redisMap.put(cadastroModel.getCpf(), objectMapper.convertValue(cadastroModel, Map.class));
                cadastroRedisRepository.insertHashKey(KEY_NAME, redisMap);
            }
            return new ResponseEntity<>(cadastroRepository.findAll(),HttpStatus.OK);
        }else {
            LoggerConfig.setErrorLog(
                    "/cadastro/v1/getAll",
                    STATUS_CODE_NOT_FOUND,
                    "nenhum cadastro encontrado",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            throw new ApiException(STATUS_CODE_NOT_FOUND,"nenhum cadastro encontrado.");
        }
    }

    @Override
    public ResponseEntity<?> findByEmail(String email) {
        if (dataValidationUtil.validateObjectStringIsEmpty(email)){
            CadastroModel cadastroModel = cadastroRepository.findByEmail(email);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/getbyEmail",
                        STATUS_CODE_OK,
                        "cadastro puxado com sucesso pelo email",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(cadastroModel,HttpStatus.OK);
            }else {
                LoggerConfig.setErrorLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/getbyEmail",
                        STATUS_CODE_NOT_FOUND,
                        "Dados nao existente.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                throw new ApiException(STATUS_CODE_NOT_FOUND,"Dados não existem.");
            }
        }else {
            LoggerConfig.setErrorLog(
                    "/cadastro/v1/getbyEmail",
                    STATUS_CODE_BAD_REQUEST,
                    "Email nao fornecido.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            throw new ApiException(STATUS_CODE_BAD_REQUEST,"Email nao fornecido");
        }
    }

    @Override
    public ResponseEntity<?> findByTelephone(Long telephone) {
        if (telephone == null){
            LoggerConfig.setAnalyticsLog(
                    "/cadastro/v1/getByTelephone",
                    STATUS_CODE_BAD_REQUEST,
                    "Telephone nao inserido.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            throw new ApiException(STATUS_CODE_BAD_REQUEST,"Não passou o telephone");
        }else{
            LoggerConfig.setErrorLog(
                    "/cadastro/v1/getByTelephone",
                    STATUS_CODE_NOT_FOUND,
                    "Dado inserido, nao encontrado.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            CadastroModel cadastroModel = cadastroRepository.findByTelephone(telephone);
            if (cadastroModel == null){
                throw new ApiException(STATUS_CODE_NOT_FOUND,"Telephone nao encontrado");
            }else{
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/getByTelephone"
                        ,STATUS_CODE_OK,
                        "cadastro puxado com sucesso pelo telephone",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(cadastroModel,HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<?> findByCpf(Long cpf) {
        if (cpf == null) {
            LoggerConfig.setErrorLog(
                    "/cadastro/v1/getByCpf",
                    STATUS_CODE_BAD_REQUEST,
                    "Cpf nao inserido.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            throw new ApiException(STATUS_CODE_BAD_REQUEST, "Insira o cpf.");
        } else {
            LoggerConfig.setErrorLog(
                    "/cadastro/v1/getByTelephone",
                    STATUS_CODE_NOT_FOUND,
                    "Dados não existe.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            CadastroModel cadastroModel = cadastroRepository.findByCpf(cpf);
            if (cadastroModel == null) {
                throw new ApiException(STATUS_CODE_NOT_FOUND, "Cadastro não encontrado");
            } else {
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1 + "/getByTelephone"
                        , STATUS_CODE_OK,
                        "cadastro puxado com sucesso pelo telephone",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(cadastroModel, HttpStatus.OK);
            }
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateByCpf(Long cpf, CadastroDto cadastroDto) {
        System.out.println();
        if (dataValidationUtil.validateObjectDataToUpdate(cadastroDto)){
            CadastroModel cadastroModel = cadastroRepository.findByCpf(cpf);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/updateByCpf",
                        STATUS_CODE_OK,
                        "cadastro atualizado",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                cadastroRepository.updateBycpf(
                        cadastroDto.getName(),
                        cadastroDto.getAddress(),
                        cadastroDto.getNascimento(),
                        cadastroDto.getEmail(),
                        cadastroDto.getCpf(),
                        cadastroDto.getSex(),
                        cadastroDto.getTelephone());
                ResponseSuccess responseSuccess = new ResponseSuccess(STATUS_CODE_OK,"Cadastro atualizado com sucesso");
                return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
            }else {
                throw new ApiException(STATUS_CODE_NOT_FOUND,"Cpf nao existe no banco para atualizar.");
            }
        }else {
            LoggerConfig.setErrorLog(
                    "/cadastro/v1/updateByCpf",
                    STATUS_CODE_BAD_REQUEST,
                    "Dados nao insiridos corretamente.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            throw new ApiException(STATUS_CODE_BAD_REQUEST,"Cpf não inserido");
        }
    }

    @Override
    public ResponseEntity<?> updateByEmail(String email, CadastroDto cadastroDto) {
        if (!email.isEmpty() &&
                cadastroDto.getCpf() != null &&
                !cadastroDto.getName().isEmpty() &&
                !cadastroDto.getAddress().isEmpty() &&
                cadastroDto.getNascimento() != null &&
                cadastroDto.getTelephone() != null &&
                !cadastroDto.getEmail().isEmpty() &&
                !cadastroDto.getSex().isEmpty()) {
            LoggerConfig.setErrorLog(ENDPOINT_DEFAULT_CONTROLLER_V1+"/updateByEmail",
                    STATUS_CODE_BAD_REQUEST,
                    "Insira o email",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            CadastroModel cadastroModel = cadastroRepository.findByEmail(email);

            if (cadastroModel != null) {

                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/updateByEmail",
                        STATUS_CODE_OK,
                        "cadastro atualizado com sucesso",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());

                cadastroRepository.updateByEmail(
                        cadastroDto.getName(),
                        cadastroDto.getAddress(),
                        cadastroDto.getNascimento(),
                        cadastroDto.getEmail(),
                        cadastroDto.getCpf(),
                        cadastroDto.getSex(),
                        cadastroDto.getTelephone());

                ResponseSuccess responseSuccess = new ResponseSuccess(STATUS_CODE_OK, "Cadastro atualizado.");
                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            } else {
                throw new ApiException(STATUS_CODE_NOT_FOUND, "Email não encontrado");
            }
        } else {
            throw new ApiException(STATUS_CODE_BAD_REQUEST, "Email não inserido.");
        }
    }

    @Override
    public ResponseEntity<?> updateByTelephone (Long telephone, CadastroDto cadastroDto) {
            if (telephone != null &&
                    !cadastroDto.getName().isEmpty() &&
                    cadastroDto.getCpf() != null &&
                    cadastroDto.getNascimento() != null &&
                    cadastroDto.getTelephone() != null &&
                    !cadastroDto.getEmail().isEmpty() &&
                    !cadastroDto.getAddress().isEmpty() &&
                    !cadastroDto.getSex().isEmpty()){

                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/updateBytelefone",
                        STATUS_CODE_BAD_REQUEST,
                        "Insira o telephone",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                CadastroModel cadastroModel = cadastroRepository.findByTelephone(telephone);
                if (cadastroModel.getTelephone() != null) {
                    LoggerConfig.setAnalyticsLog(
                            ENDPOINT_DEFAULT_CONTROLLER_V1+"/updateBytelefone",
                            STATUS_CODE_OK,
                            "telefone atualizado com sucesso",
                            LocalDateTime.now().toString(),
                            LocalDateTime.now().toString());

                    cadastroRepository.updateByTelephone(
                            cadastroDto.getName(),
                            cadastroDto.getCpf(),
                            cadastroDto.getNascimento(),
                            cadastroDto.getTelephone(),
                            cadastroDto.getEmail(),
                            cadastroDto.getAddress(),
                            cadastroDto.getSex());
                    ResponseSuccess responseSuccess = new ResponseSuccess(STATUS_CODE_OK,"Cadastro atualizado com sucesso.");
                    return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
                }else {
                    throw new ApiException(STATUS_CODE_NOT_FOUND,"Telephone não foi encontrado.");
                }
            }else {
                throw new ApiException(STATUS_CODE_BAD_REQUEST,"Insira o telephone");
            }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteByEmail(String email) {
        System.out.println();
        if (dataValidationUtil.validateObjectStringIsEmpty(email)){
            LoggerConfig.setAnalyticsLog(
                    ENDPOINT_DEFAULT_CONTROLLER_V1+"/deleteByEmail",
                    STATUS_CODE_BAD_REQUEST,
                    "Insira o Email.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            CadastroModel cadastroModel = cadastroRepository.findByEmail(email);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/updateBytelefone",
                        STATUS_CODE_OK,
                        "Cadastro deletado com sucesso.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                cadastroRepository.deleteByEmail(email);
                ResponseSuccess responseSuccess = new ResponseSuccess(200,"cadastro deletado");
                return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
            }else {
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1+"/deleteByEmail",
                        STATUS_CODE_NOT_FOUND,
                        "Insira o Cadastro da empresa não existe.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                throw new ApiException(STATUS_CODE_NOT_FOUND,"cadastro não encontrado");
            }
        }else{
            throw new ApiException(STATUS_CODE_BAD_REQUEST,"Insira o email");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteByCpf(Long cpf) {
        if (cpf != null) {
            LoggerConfig.setAnalyticsLog(
                    ENDPOINT_DEFAULT_CONTROLLER_V1+"/deleteByCpf",
                    STATUS_CODE_BAD_REQUEST,
                    "Insira o cpf.",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            CadastroModel cadastroModel = cadastroRepository.findByCpf(cpf);
            if (cadastroModel != null) {
                LoggerConfig.setAnalyticsLog(
                        ENDPOINT_DEFAULT_CONTROLLER_V1 + "/updateBytelefone",
                        STATUS_CODE_OK,
                        "Cadastro deletado com sucesso.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                cadastroRepository.deleteByCpf(cpf);
                ResponseSuccess responseSuccess = new ResponseSuccess(STATUS_CODE_OK, "Cadastro deletado.");
                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            } else {
                throw new ApiException(STATUS_CODE_NOT_FOUND, "Cadastro nao encontrado.");
            }
        } else {
            throw new ApiException(STATUS_CODE_BAD_REQUEST, "Insira o cpf.");
        }

    }

    public void delete(CadastroModel cadastroModel) {
        cadastroRepository.delete(cadastroModel);
    }

}
