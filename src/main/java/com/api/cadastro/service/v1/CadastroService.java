package com.api.cadastro.service.v1;


import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.dto.CadastroDto;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface CadastroService {


    ResponseEntity<?> save(CadastroModel cadastroModel);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findByEmail(String email);

    ResponseEntity<?> findByTelephone(Long telephone);

    ResponseEntity<?> findByCpf(Long cpf);

    @Transactional
    ResponseEntity<?> updateByCpf(Long cpf, CadastroDto cadastroDto);

    ResponseEntity<?> updateByEmail(String email, CadastroDto cadastroDto);

    ResponseEntity<?> updateByTelephone(Long telephone, CadastroDto cadastroDto);

    ResponseEntity<?> deleteByEmail(String email);

    ResponseEntity<?> deleteByCpf(Long cpf);

     void delete(CadastroModel cadastroModel);

}
