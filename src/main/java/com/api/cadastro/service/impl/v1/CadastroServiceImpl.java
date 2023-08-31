package com.api.cadastro.service.impl.v1;


import com.api.cadastro.config.LoggerConfig;
import com.api.cadastro.domain.model.v1.ApiException;
import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.domain.repository.v1.CadastroRepository;
import com.api.cadastro.dto.CadastroDto;
import com.api.cadastro.dto.ResponseSuccess;
import com.api.cadastro.service.v1.CadastroService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class CadastroServiceImpl implements CadastroService{

    final CadastroRepository cadastroRepository;

    public CadastroServiceImpl(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    @Override
    public ResponseEntity<?> save(CadastroModel cadastroModel) {
        if (cadastroModel.getCpf().isEmpty()){
            throw new ApiException(400, "Campos nao inseridos na requisição.");
        }else {
            CadastroModel cadastroModel1 = cadastroRepository.findByCpf(cadastroModel.getCpf());
            if (cadastroModel1 != null){
                cadastroRepository.save(cadastroModel);
                ResponseSuccess responseSuccess = new ResponseSuccess(200,"Cadastro salvo com sucesso");
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/save",
                        "200",
                        "cadastro salvo com sucesso",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            }else{
                throw new ApiException(400, "Usuario ja esta salvo.");
            }
        }
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<CadastroModel> cadastroModels =  cadastroRepository.findAll();
        if (cadastroModels.size() > 0){
            LoggerConfig.setAnalyticsLog(
                    "/cadastro/v1/getAll",
                    "200",
                    "Todos os cadastro foram puxado com sucesso",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().toString());
            return new ResponseEntity<>(cadastroRepository.findAll(),HttpStatus.OK);
        }else {
            throw new ApiException(404,"nenhum cadastro encontrado.");
        }
    }

    @Override
    public ResponseEntity<?> findByEmail(String email) {
        if (!email.isEmpty()){
            CadastroModel cadastroModel = cadastroRepository.findByEmail(email);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/getbyEmail",
                        "200",
                        "cadastro puxado com sucesso pelo email",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(cadastroModel,HttpStatus.OK);
            }else {
                throw new ApiException(400,"Email nao encontrado no banco.");
            }
        }else {
            throw new ApiException(400,"Email nao fornecido");
        }
    }

    @Override
    public ResponseEntity<?> findByTelephone(Integer telephone) {
        if (telephone == null){
            throw new ApiException(400,"Não passou o telephone");
        }else{
            CadastroModel cadastroModel = cadastroRepository.findByTelephone(telephone);
            if (cadastroModel == null){
                throw new ApiException(404,"Telephone nao encontrado");
            }else{
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/getByTelephone"
                        ,"200",
                        "cadastro puxado com sucesso pelo telephone",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(cadastroModel,HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<?> findByCpf(String cpf) {
        if (!cpf.isEmpty()){
            CadastroModel cadastroModel = cadastroRepository.findByCpf(cpf);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/getByCpf",
                        "200",
                        "cadastro puxado com sucesso pelo cpf",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                return new ResponseEntity<>(cadastroModel,HttpStatus.OK);
            }else{
                throw new ApiException(404,"cpf nao encontrado");
            }
        }else
            throw new ApiException(400,"Insira o cpf.");
    }


    @Transactional
    @Override
    public ResponseEntity<?> updateByCpf(String cpf, CadastroDto cadastroDto) {
        if (!cpf.isEmpty() &&
                !cadastroDto.getCpf().isEmpty() &&
                !cadastroDto.getName().isEmpty() &&
                !cadastroDto.getAddress().isEmpty() &&
                !cadastroDto.getDate().isEmpty() &&
                cadastroDto.getTelephone() != null &&
                !cadastroDto.getEmail().isEmpty() &&
                !cadastroDto.getSex().isEmpty()){
            CadastroModel cadastroModel = cadastroRepository.findByCpf(cpf);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/updateByCpf",
                        "200",
                        "cadastro atualizado",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                cadastroRepository.updateBycpf(
                        cadastroDto.getName(),
                        cadastroDto.getAddress(),
                        cadastroDto.getDate(),
                        cadastroDto.getEmail(),
                        cadastroDto.getCpf(),
                        cadastroDto.getSex(),
                        cadastroDto.getTelephone());
                ResponseSuccess responseSuccess = new ResponseSuccess(200,"Cadastro atualizado com sucesso");
                return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
            }else {
                throw new ApiException(404,"cpf nao existe no banco para atualizar.");
            }
        }else {
            throw new ApiException(400,"Cpf não inserido");
        }
    }

    @Override
    public ResponseEntity<?> updateByEmail(String email, CadastroDto cadastroDto) {
        if (!email.isEmpty() &&
                !cadastroDto.getCpf().isEmpty() &&
                !cadastroDto.getName().isEmpty() &&
                !cadastroDto.getAddress().isEmpty() &&
                !cadastroDto.getDate().isEmpty() &&
                cadastroDto.getTelephone() != null &&
                !cadastroDto.getEmail().isEmpty() &&
                !cadastroDto.getSex().isEmpty()) {

            CadastroModel cadastroModel = cadastroRepository.findByEmail(email);

            if (cadastroModel != null) {

                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/updateByEmail",
                        "200",
                        "cadastro atualizado com sucesso",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());

                cadastroRepository.updateByEmail(
                        cadastroDto.getName(),
                        cadastroDto.getAddress(),
                        cadastroDto.getDate(),
                        cadastroDto.getEmail(),
                        cadastroDto.getCpf(),
                        cadastroDto.getSex(),
                        cadastroDto.getTelephone());

                ResponseSuccess responseSuccess = new ResponseSuccess(200, "Cadastro atualizado.");
                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            } else {
                throw new ApiException(404, "Email não encontrado");
            }
        } else {
            throw new ApiException(400, "Email não inserido.");
        }
    }

    @Override
    public ResponseEntity<?> updateByTelephone (Integer telephone, CadastroDto cadastroDto) {
            if (telephone != null &&
                    cadastroDto.getName().isEmpty() &&
                    cadastroDto.getCpf().isEmpty() &&
                    cadastroDto.getDate().isEmpty() &&
                    cadastroDto.getTelephone() != null &&
                    cadastroDto.getEmail().isEmpty() &&
                    cadastroDto.getAddress().isEmpty() &&
                    cadastroDto.getSex().isEmpty()){

                CadastroModel cadastroModel = cadastroRepository.findByTelephone(telephone);
                if (cadastroModel.getTelephone() != null) {
                    LoggerConfig.setAnalyticsLog(
                            "/cadastro/v1/updateBytelefone",
                            "200",
                            "telefone atualizado com sucesso",
                            LocalDateTime.now().toString(),
                            LocalDateTime.now().toString());

                    cadastroRepository.updateByTelephone(
                            cadastroDto.getName(),
                            cadastroDto.getCpf(),
                            cadastroDto.getDate(),
                            cadastroDto.getTelephone(),
                            cadastroDto.getEmail(),
                            cadastroDto.getAddress(),
                            cadastroDto.getSex());
                    ResponseSuccess responseSuccess = new ResponseSuccess(200,"Cadastro atualizado com sucesso.");
                    return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
                }else {
                    throw new ApiException(404,"Telephone não foi encontrado.");
                }
            }else {
                throw new ApiException(400,"Insira o telephone");
            }
    }

    @Override
    public ResponseEntity<?> deleteByEmail(String email) {
        if (!email.isEmpty()){
            CadastroModel cadastroModel = cadastroRepository.findByEmail(email);
            if (cadastroModel != null){
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/updateBytelefone",
                        "200",
                        "Cadastro deletado com sucesso.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                cadastroRepository.delete(cadastroModel);
                ResponseSuccess responseSuccess = new ResponseSuccess(200,"cadastro deletado");
                return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
            }else {
                throw new ApiException(404,"cadastro não encontrado");
            }
        }else{
            throw new ApiException(400,"Insira o email");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteByCpf(String cpf) {
        if (!cpf.isEmpty()) {
            CadastroModel cadastroModel = cadastroRepository.findByCpf(cpf);
            if (cadastroModel != null) {
                LoggerConfig.setAnalyticsLog(
                        "/cadastro/v1/updateBytelefone",
                        "200",
                        "Cadastro deletado com sucesso.",
                        LocalDateTime.now().toString(),
                        LocalDateTime.now().toString());
                cadastroRepository.delete(cadastroModel);
                ResponseSuccess responseSuccess = new ResponseSuccess(200, "Cadastro deletado.");
                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            } else {
                throw new ApiException(404, "Cadastro nao encontrado.");
            }
        } else {
            throw new ApiException(400, "Insira o cpf.");
        }

    }


    public void delete(CadastroModel cadastroModel) {
        cadastroRepository.delete(cadastroModel);
    }


}
