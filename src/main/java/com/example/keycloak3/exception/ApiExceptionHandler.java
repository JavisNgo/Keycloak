package com.example.keycloak3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ApiException ex = new ApiException(
                request.getMessage(),
                request,
                status
        );
        return new ResponseEntity<>(ex, status);
    }
}
