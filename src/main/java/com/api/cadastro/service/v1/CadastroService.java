package com.api.cadastro.service.v1;


import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.dto.CadastroDto;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface CadastroService {


    ResponseEntity<?> save(CadastroModel cadastroModel);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findByEmail(String email);

    ResponseEntity<?> findByTelephone(Integer telephone);

    ResponseEntity<?> findByCpf(String cpf);

    ResponseEntity<?> updateByCpf(String cpf, CadastroDto cadastroDto);


    ResponseEntity<?> updateByEmail(String email, CadastroDto cadastroDto);

    ResponseEntity<?> updateByTelephone(Integer telephone, CadastroDto cadastroDto);

    ResponseEntity<?> deleteByEmail(String email);

    ResponseEntity<?> deleteByCpf(String cpf);

     void delete(CadastroModel cadastroModel);

}
