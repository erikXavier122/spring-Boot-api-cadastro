package com.api.cadastro.dto;

import java.time.Instant;

public class ApiErrorResponse {


    private int status;
    private String message;
    private Instant timestamp;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(int status, String message, Instant now) {
        this.status= status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

}
