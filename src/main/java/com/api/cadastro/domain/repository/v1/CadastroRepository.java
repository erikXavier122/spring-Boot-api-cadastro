package com.api.cadastro.domain.repository.v1;

import com.api.cadastro.domain.model.v1.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel, UUID> {


    @Query(value = "Select * FROM tb_cadastro WHERE email = ?1",nativeQuery = true)
    Optional<CadastroModel> findByEmail(String email);


    @Query(value = "Select * FROM tb_cadastro WHERE telephone = ?1",nativeQuery = true)
    Optional<CadastroModel> findByTelephone(Integer telephone);

    @Query(value = "Select * FROM tb_cadastro WHERE cpf = ?1",nativeQuery = true)
    Optional<CadastroModel> findByCpf(String cpf);

    @Modifying
    @Query(value = "UPDATE tb_cadastro SET name = ?1, address = ?2, date = ?3, email = ?4, cpf = ?5, sex = ?6, telephone = ?7 WHERE cpf ?1" ,nativeQuery = true)
    void updateBycpf(String name, String address, String date, String email, String cpf, String sex, Integer telephone);

}
