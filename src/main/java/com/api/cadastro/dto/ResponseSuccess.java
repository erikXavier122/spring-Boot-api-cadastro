package com.api.cadastro.dto;

public class ResponseSuccess {

    private int status;
    private String msg;

    public ResponseSuccess(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseSuccess() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
