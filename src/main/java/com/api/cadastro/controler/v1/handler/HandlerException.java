package com.api.cadastro.controler.v1.handler;


import com.api.cadastro.domain.model.v1.ApiException;
import com.api.cadastro.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    private static final HttpStatus UNPROCESSABLE_ENTITY = HttpStatus.UNPROCESSABLE_ENTITY;


    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        return new ResponseEntity(new ApiErrorResponse(ex.getStatus(), ex.getMessage(), Instant.now()), HttpStatus.valueOf(ex.getStatus()));
    }


}
