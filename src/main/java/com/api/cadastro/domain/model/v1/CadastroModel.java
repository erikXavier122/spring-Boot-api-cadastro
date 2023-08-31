package com.api.cadastro.domain.model.v1;




import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


//DADOS CADASTRAIS QUE PRECISO
//Nome.E-mail.Telefone.Endereço.Data de nascimento.Sexo.CPF.

@Entity
@Table(name = "tb_cadastro")

public class CadastroModel implements Serializable {


    private static final Long SerialVersionUId =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 70)
    private String name;

    @Column(unique = true,nullable = false,length = 70)
    private String email;

    @Column(nullable = false,unique = true,length = 20)
    private Integer telephone;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(length = 10)
    private String date;

    @Column(nullable = false,length = 1)
    private String sex;

    @Column(nullable = false,unique = true,length = 11)
    private String cpf;

    public CadastroModel() {
    }

    public CadastroModel(UUID id, String name, String email, Integer telephone, String address, String date, String sex, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.date = date;
        this.sex = sex;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
