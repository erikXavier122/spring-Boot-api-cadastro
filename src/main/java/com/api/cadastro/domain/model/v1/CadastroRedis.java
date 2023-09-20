package com.api.cadastro.domain.model.v1;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;


public class CadastroRedis implements Serializable {
//    nome email telephone address date sex cpf

    private Long cpf;
    private String nome;
    private String email;
    private Long telephone;
    private String address;
    private String date;
    private String sex;

    public CadastroRedis() {
    }


    public CadastroRedis(Long cpf, String nome, String email, Long telephone, String address, String date, String sex) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.date = date;
        this.sex = sex;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
