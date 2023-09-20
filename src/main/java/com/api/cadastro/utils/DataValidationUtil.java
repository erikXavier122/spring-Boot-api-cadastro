package com.api.cadastro.utils;

import com.api.cadastro.domain.model.v1.CadastroModel;
import com.api.cadastro.dto.CadastroDto;
import io.micrometer.core.instrument.util.StringUtils;

import org.springframework.stereotype.Component;

@Component
public class DataValidationUtil {

    public Boolean validationDatasToSave(CadastroModel cadastroModel) {
        System.out.println();
        if (StringUtils.isEmpty(cadastroModel.getName()) &&
                StringUtils.isEmpty(cadastroModel.getAddress()) &&
                StringUtils.isEmpty(cadastroModel.getEmail()) &&
                cadastroModel.getEmail() == null &&
                cadastroModel.getTelephone() == null &&
                cadastroModel.getNascimento().isEmpty() &&
                StringUtils.isEmpty(cadastroModel.getSex()) &&
                cadastroModel.getCpf() == null) {
            return false;
        }else if (StringUtils.isEmpty(cadastroModel.getName())) {
            return false;
        }else if (StringUtils.isEmpty(cadastroModel.getAddress())) {
            return false;
        }else if (StringUtils.isEmpty(cadastroModel.getEmail())) {
            return false;
        }else if(StringUtils.isEmpty(cadastroModel.getSex())){
            return false;
        }else if (cadastroModel.getEmail() == null) {
            return false;
        }else if (cadastroModel.getTelephone() == null ) {
            return false;
        }else if (cadastroModel.getNascimento().isEmpty()) {
            return false;
        }else if (cadastroModel.getCpf() == null ) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean validateObjectStringIsEmpty(String valor){
        if (valor.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public boolean validateObjectDataToUpdate(CadastroDto cadastroDto) {
        if (cadastroDto.getName().isEmpty() &&
                cadastroDto.getCpf() == null &&
                cadastroDto.getSex().isEmpty() &&
                cadastroDto.getNascimento() == null &&
                cadastroDto.getTelephone() == null &&
                cadastroDto.getAddress().isEmpty() &&
                cadastroDto.getEmail().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
