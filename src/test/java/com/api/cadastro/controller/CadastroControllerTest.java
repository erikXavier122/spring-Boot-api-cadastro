package com.api.cadastro.controller;

import com.api.cadastro.controler.v1.CadastroControler;
import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.service.v1.CadastroService;
import io.restassured.http.ContentType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.UUID;


@WebMvcTest
public class CadastroControllerTest {


    @Autowired
    private CadastroControler cadastroControler;

//  graças ao mockBeean nos criamos uma implementação falsa da nossa service, nos vamos fazer isso para ter um limite de uso da nossa service
    @MockBean
    private CadastroService cadastroService;

//  vai subir o contexto spring mas ultilize apenas o cadastroControler, para deixar nossa aplicação mais rapida
    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.cadastroControler);
    }

//  é um metodo que quando nos rodarmos o comando get e rodar a uri /cadastro... ele vai retornar um ok, status code 200
    @Test
    public void TestEndpointGetByEmail() {

//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
             .when()
                .get("/cadastro/v1/getByEmail/{email}","euaqui@gmail.com" )
             .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointGetByCpf() {

//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .get("/cadastro/v1/getByCpf/{cpf}","12345678901" )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointGetByTelephone() {

//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .get("/cadastro/v1/getByTelephone/{telephone}",1999234478)
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointGetAll() {

//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .get("/cadastro/v1/getAll")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointDeleteByEmail() {


//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .delete("/cadastro/v1/deleteByEmail/{email}","euaqui@gmail.com" )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointDeleteByCpf() {

//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .delete("/cadastro/v1/deleteByCpf/{cpf}","12345678901" )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointDeleteByTelephone() {


//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .delete("/cadastro/v1/deleteByTelephone/{telephone}",1999234478 )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointUpdateByEmail() {


//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .put("/cadastro/v1/updateByEmail/{email}","euaqui@gmail.com" )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointUpdateByCpf() {


//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .put("/cadastro/v1/updateByCpf/{cpf}","12345678901" )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointUpdateByTelephone() {


//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .put("/cadastro/v1/updateByTelephone/{telephone}",1999234478 )
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void TestEndpointSave() {


//        given: .dado essa informação .quando .fizermos um get "endpoint" .entao .retorne o status 200,OK
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when()
                .post("/cadastro/v1/save" )
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }
}
