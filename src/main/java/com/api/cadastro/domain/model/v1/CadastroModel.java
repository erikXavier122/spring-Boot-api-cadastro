package com.api.cadastro.domain.model.v1;





import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


//DADOS CADASTRAIS QUE PRECISO
//Nome.E-mail.Telefone.Endereço.Data de nascimento.Sexo.CPF.

@Entity
@Table(name = "tb_cadastro")

public class CadastroModel extends BaseEntity implements Serializable {


    private static final Long SerialVersionUId =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 70,nullable = false)
    private String name;

    @Column(unique = true,nullable = false,length = 70)
    private String email;

    @Column(unique = true)
    private Long telephone;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(length = 10)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String nascimento;

    @Column(nullable = false,length = 1)
    private String sex;

    @Column(unique = true, length = 11)
    private Long cpf;

    public CadastroModel() {
    }

    public CadastroModel(String name, String email, Long telephone, String address, String nascimento, String sex, Long cpf) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.nascimento = nascimento;
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
