package com.challenge.simplecrud.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Indica ao spring para chamar essa classe toda vez que disparar uma exception
public class RequestsExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(){
        return ResponseEntity.badRequest().body("Dado não encontrado.");
    }}
