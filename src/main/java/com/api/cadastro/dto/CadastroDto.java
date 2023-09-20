package com.api.cadastro.dto;


import com.api.cadastro.domain.model.v1.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.time.LocalDate;

public class CadastroDto extends BaseEntity {

    @Column(length = 70,nullable = false)
    private String name;

    @Column(unique = true,nullable = false,length = 70)
    private String email;

    @Column(unique = true)
    private Long telephone;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(length = 10)
    private String nascimento;

    @Column(nullable = false,length = 1)
    private String sex;

    @Column(unique = true)
    private Long cpf;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonFormat(pattern = "dd-MM-yyyy")
    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
