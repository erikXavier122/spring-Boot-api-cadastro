package com.api.cadastro.controler.v1;


import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.dto.CadastroDto;
import com.api.cadastro.service.v1.CadastroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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
    public ResponseEntity<?> saveCadastros(@RequestBody @Valid CadastroDto cadastroDto){
        CadastroModel cadastroModel=new CadastroModel();
        BeanUtils.copyProperties(cadastroDto,cadastroModel);
        return cadastroService.save(cadastroModel);
    }

    @Operation(summary = "Puxa todos os cadastros",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Puxou todos os cadastros"),
    })
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return cadastroService.findAll();
    }

    @Operation(summary = "Puxar um cadastro por email expecifico",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "cadastro encontrado com sucesso."),
            @ApiResponse(responseCode = "404",description = "cadastro nao existe!"),
    })
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable(value = "email")String email){
        return cadastroService.findByEmail(email);
    }


    @Operation(summary = "Puxar cadastro pelo telefone",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "cadastro encontrado com sucesso."),
            @ApiResponse(responseCode = "404",description = "cadastro nao existe!")
    })
    @GetMapping("/getByTelephone/{telephone}")
    public ResponseEntity<?> getByTelephone(@PathVariable(value = "telephone")Integer telephone){
        return cadastroService.findByTelephone(telephone);
    }

    @Operation(summary = "Puxar cadastro pelo cpf",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro puxado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Cadastro nao existe"),
    })
    @GetMapping("/getByCpf/{cpf}")
    public ResponseEntity<?> getByCpf(@PathVariable(value = "cpf")String cpf){
        return cadastroService.findByCpf(cpf);
    }

    @Operation(summary = "Atualizar cadastro pelo email",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro nao existe"),
    })
    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity<?> updateByEmail(@PathVariable (value = "email") String email,@RequestBody @Valid CadastroDto cadastroDto){
        return cadastroService.updateByEmail(email,cadastroDto);
    }

    @Operation(summary = "Atualizar cadastros pelo cpf",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro nao existe"),
    })
    @PutMapping("/updateByCpf/{cpf}")
    public ResponseEntity<?> updateByCpf(@PathVariable(value = "cpf")String cpf,@RequestBody @Valid CadastroDto cadastroDto){
        return cadastroService.updateByCpf(cpf,cadastroDto);
    }

    @Operation(summary = "Atualizar cadastro pelo telefone",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro nao existe"),
    })
    @PutMapping("/updateByTelephone/{telephone}")
    public ResponseEntity<?> updateByTelephone(@PathVariable (value = "telephone") Integer telephone,@RequestBody @Valid CadastroDto cadastroDto){
        return cadastroService.updateByTelephone(telephone,cadastroDto);
    }

    @Operation(summary = "Deleta cadastro por email",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro apagado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Email nao encontrado"),
    })
    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<?> deleteByEmail(@PathVariable(value = "email")String email){
        return cadastroService.deleteByEmail(email);
    }

    @Operation(summary = "Deleta cadastro por Cpf",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Cadastro apagado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Cpf nao encontrado"),
    })
    @DeleteMapping("/deleteByCpf/{cpf}")
    public ResponseEntity<?> deleteByCpf(@PathVariable(value = "cpf")String cpf){
        return cadastroService.deleteByCpf(cpf);
    }



}
