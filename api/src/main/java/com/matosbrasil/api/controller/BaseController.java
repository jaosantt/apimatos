package com.matosbrasil.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.matosbrasil.api.enums.ResponseCode;
import com.matosbrasil.api.enums.ResponseMessage;

public abstract class BaseController {
	protected ResponseEntity<Map<String, Object>> createErrorResponse(HttpStatus status, Integer code, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("error", message);

        return ResponseEntity.status(status)
                             .body(response);
    }
	
	protected ResponseEntity<Map<String, Object>> createSuccessResponse(ResponseMessage responseMessage){
		// Monta a resposta
		Map<String, Object> response = new HashMap<>();
		response.put("code", ResponseCode.SUCCESS.getCode());
		response.put("message", responseMessage.getMessage());
		
		// Retorna a resposta
        return ResponseEntity.ok().body(response);
	}
}
