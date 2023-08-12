package com.api.cadastro.service.impl.v1;


import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.domain.repository.v1.CadastroRepository;
import com.api.cadastro.dto.CadastroDto;
import com.api.cadastro.service.v1.CadastroService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;



@Service
public class CadastroServiceImpl implements CadastroService{

    final CadastroRepository cadastroRepository;

    public CadastroServiceImpl(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    @Override
    public Object save(CadastroModel cadastroModel) {
        return cadastroRepository.save(cadastroModel);
    }

    @Override
    public Collection<CadastroModel> findAll() {
        return cadastroRepository.findAll();
    }

    @Override
    public Optional<CadastroModel> findByEmail(String email) {
        return cadastroRepository.findByEmail(email);
    }

    @Override
    public Optional<CadastroModel> findByTelephone(Integer telephone) {
        return cadastroRepository.findByTelephone(telephone);
    }

    @Override
    public Optional<CadastroModel> findByCpf(String cpf) {
        return cadastroRepository.findByCpf(cpf);
    }


    @Transactional
    @Override
    public Object updateByCpf(String cpf, CadastroDto cadastroDto) {
        Optional<CadastroModel> cadastroModelOptional=findByCpf(cpf);
        if (cadastroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cpf nao encontrado.");
        }
        cadastroRepository.updateBycpf(cadastroDto.getName(),cadastroDto.getAddress(),cadastroDto.getDate(),cadastroDto.getEmail(),cadastroDto.getCpf(),cadastroDto.getSex(),cadastroDto.getTelephone());
        return "Cadastro da empresa atualizado.";
    }

    @Override
    public Object updateByEmail(String email, CadastroDto cadastroDto) {
        Optional<CadastroModel> cadastroModelOptional=findByEmail(email);
        if (cadastroModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro atualizado.");
    }

    @Override
    public Object updateByTelephone(Integer telephone, CadastroDto cadastroDto) {
        Optional<CadastroModel> cadastroModelOptional=findByTelephone(telephone);
        if (cadastroModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro atualizado.");
    }

    @Override
    public Object deleteByEmail(String email) {
        Optional<CadastroModel> cadastroModelOptional = findByEmail(email);
        System.out.println(cadastroModelOptional);
        if (cadastroModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email nao existe.");
        }
        delete(cadastroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastr oapagadoi com sucesso");
    }

    @Transactional
    @Override
    public Object deleteByCpf(String cpf) {
        Optional<CadastroModel>cadastroModelOptional=findByCpf(cpf);
        System.out.println(cadastroModelOptional);
        if (cadastroModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cpf nao existe.");
        }
        delete(cadastroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro apagado com sucesso.");
    }

    @Transactional
    @Override
    public Object deleteByTelephone(Integer telephone) {
        Optional<CadastroModel> cadastroModelOptional = findByTelephone(telephone);
        System.out.println(cadastroModelOptional);
        if (cadastroModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone nao existe");
        }
        delete(cadastroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro deletado com sucesso");
    }

    @Override
    public void delete(CadastroModel cadastroModel) {
        cadastroRepository.delete(cadastroModel);
    }


}
