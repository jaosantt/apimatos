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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.matosbrasil.api.enums.ResponseCode;

@ControllerAdvice
public class ValidacaoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = "error";
            String mensagem = error.getDefaultMessage();
            errorResponse.put(campo, mensagem);
            errorResponse.put("code", ResponseCode.ERROR.getCode());
        });
        
        // Retorna a resposta com BAD_REQUEST e os headers com o Content-Type configurado
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Valores de parâmetros inválidos.");
        errorResponse.put("code", ResponseCode.ERROR.getCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }
}
