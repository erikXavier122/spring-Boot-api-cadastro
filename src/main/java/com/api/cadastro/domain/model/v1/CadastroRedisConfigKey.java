package com.api.cadastro.domain.model.v1;

import java.io.Serializable;


public class CadastroRedisConfigKey implements Serializable {

    private String startTime;

    public CadastroRedisConfigKey( String startTime) {

        this.startTime = startTime;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


}
