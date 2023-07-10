package com.api.cadastro.controler.v1;


import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.dto.CadastroDto;
import com.api.cadastro.service.v1.CadastroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cadastro/v1")
public class CadastroControler {

    private final CadastroService cadastroService;


    public CadastroControler(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @Operation(summary = "Realiza o upload de arquivos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro feito com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping("/save")
    public ResponseEntity<Object> saveCadastros(@RequestBody @Valid CadastroDto cadastroDto){
        CadastroModel cadastroModel=new CadastroModel();
        BeanUtils.copyProperties(cadastroDto,cadastroModel);
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.save(cadastroModel));
    }

    @Operation(summary = "Puxa todos os cadastros",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Puxou todos os cadastros"),
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<CadastroModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.findAll());
    }

    @Operation(summary = "Puxar um cadastro por email expecifico",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "cadastro encontrado com sucesso."),
            @ApiResponse(responseCode = "200",description = "cadastro nao existe!"),
    })
    @GetMapping("/getbyEmail/{email}")
    public ResponseEntity<Object> getByEmail(@PathVariable(value = "email")String email){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.findByEmail(email));
    }


    @Operation(summary = "Puxar cadastro pelo telefone",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "cadastro encontrado com sucesso."),
            @ApiResponse(responseCode = "404",description = "cadastro nao existe!")
    })
    @GetMapping("/getbyTelephone/{telephone}")
    public ResponseEntity<Object> getByTelephone(@PathVariable(value = "telephone")Integer telephone){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.findByTelephone(telephone));
    }

    @Operation(summary = "Puxar cadastro pelo cpf",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro puxado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Cadastro nao existe"),
    })
    @GetMapping("/getByCpf/{cpf}")
    public ResponseEntity<Object> getByCpf(@PathVariable(value = "cpf")String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.findByCpf(cpf));
    }

    @Operation(summary = "Atualizar cadastro pelo email",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro nao existe"),
    })
    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity<Object> updateByEmail(@PathVariable (value = "email") String email,@RequestBody @Valid CadastroDto cadastroDto){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.updateByEmail(email,cadastroDto));
    }

    @Operation(summary = "Atualizar cadastros pelo cpf",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro nao existe"),
    })
    @PutMapping("/updateByCpf/{cpf}")
    public ResponseEntity<Object> updateByCpf(@PathVariable(value = "cpf")String cpf,@RequestBody @Valid CadastroDto cadastroDto){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.updateByCpf(cpf,cadastroDto));
    }

    @Operation(summary = "Atualizar cadastro pelo telefone",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro nao existe"),
    })
    @PutMapping("/updateByTelephone/{telephone}")
    public ResponseEntity<Object> updateByTelephone(@PathVariable (value = "telephone") Integer telephone,@RequestBody @Valid CadastroDto cadastroDto){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.updateByTelephone(telephone,cadastroDto));
    }

    @Operation(summary = "Deleta cadastro por email",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro apagado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Email nao encontrado"),
    })
    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<Object> deleteByEmail(@PathVariable(value = "email")String email){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.deleteByEmail(email));
    }


    @Operation(summary = "Deleta cadastro por telefone",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro apagado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Telefone nao encontrado"),
    })
    @DeleteMapping("/deleteByTelephone/{telephone}")
    public ResponseEntity<Object> deleteByTelephone(@PathVariable(value = "telephone")Integer telephone){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.deleteByTelephone(telephone));
    }

    @Operation(summary = "Deleta cadastro por Cpf",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro apagado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Cpf nao encontrado"),
    })
    @DeleteMapping("/deleteByCpf/{cpf}")
    public ResponseEntity<Object> deleteByCpf(@PathVariable(value = "cpf")String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroService.deleteByCpf(cpf));
    }



}
