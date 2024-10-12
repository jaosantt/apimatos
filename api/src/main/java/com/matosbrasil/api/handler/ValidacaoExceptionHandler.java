package com.matosbrasil.api.handler;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matosbrasil.api.enums.ResponseCode;

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
        
        // Define o Content-Type como application/json e charset UTF-8
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        // Retorna a resposta com BAD_REQUEST e os headers com o Content-Type configurado
        return new ResponseEntity<>(erros, headers, HttpStatus.BAD_REQUEST);
    }
}
