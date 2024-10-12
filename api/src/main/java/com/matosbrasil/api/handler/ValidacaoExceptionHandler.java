package com.matosbrasil.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matosbrasil.api.enums.ResponseCode;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidacaoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = "error";
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
            erros.put("code", ResponseCode.ERROR.getCode());
        });
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }
}
