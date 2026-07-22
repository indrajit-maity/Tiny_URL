package com.URI.URL_Shortner.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DomainAlreadyExistsException.class)
    public ResponseEntity<ErrorResoponse> handleDomainExits(DomainAlreadyExistsException ex){
        ErrorResoponse resoponse=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resoponse);
    }
}
