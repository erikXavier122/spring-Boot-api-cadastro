package com.api.cadastro.service.v1;


import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.dto.CadastroDto;

import java.util.Collection;
import java.util.List;

public interface CadastroService {


    Collection<CadastroModel> findAll();

    Object findByEmail(String email);

    Object findByTelephone(Integer telephone);

    Object save(CadastroModel cadastroModel);

    Object findByCpf(String cpf);

    Object updateByCpf(String cpf, CadastroDto cadastroDto);


    Object updateByEmail(String email, CadastroDto cadastroDto);

    Object updateByTelephone(Integer telephone, CadastroDto cadastroDto);

    Object deleteByEmail(String email);

    Object deleteByCpf(String cpf);

    Object deleteByTelephone(Integer telephone);

     void delete(CadastroModel cadastroModel);
}
