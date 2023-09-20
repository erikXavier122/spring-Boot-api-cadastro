package com.api.cadastro.controler.v1;

import com.api.cadastro.domain.model.v1.CadastroRedis;
import com.api.cadastro.domain.model.v1.CadastroRedisConfigKey;
import com.api.cadastro.domain.repository.v1.CadastroRedisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/redis")
public class CadastroControllerRedisTest {

    final CadastroRedisRepository cadastroRedisRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public CadastroControllerRedisTest(CadastroRedisRepository cadastroRedisRepository) {
        this.cadastroRedisRepository = cadastroRedisRepository;
    }

    @Operation(summary = "TEste", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro feito com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválida"),
    })

    @PostMapping("/save/topic")
    public String testRedisSaveHere(String keyName,CadastroRedisConfigKey cadastroRedisConfigKey){
        cadastroRedisRepository.insertKeyName(keyName,cadastroRedisConfigKey);
        return "salvado com sucesso";
    }

}
