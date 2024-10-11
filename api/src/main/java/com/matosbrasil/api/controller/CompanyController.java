package com.matosbrasil.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matosbrasil.api.dto.CompanyRequestDTO;
import com.matosbrasil.api.enums.ResponseCode;
import com.matosbrasil.api.enums.ResponseMessage;
import com.matosbrasil.api.exception.CompanyException;
import com.matosbrasil.api.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> create (@Valid @RequestBody CompanyRequestDTO body){
		try {
			// Tenta criar a empresa
			this.companyService.createCompany(body);
			
			// Monta a resposta
			Map<String, Object> response = new HashMap<>();
			response.put("code", ResponseCode.SUCCESS.getCode());
			response.put("message", ResponseMessage.SUCCESSFUL_REGISTRATION.getMessage());
			
			return ResponseEntity.ok(response);
		} catch (CompanyException companyEx) { 
			// Retorna um erro tratado
			Map<String, Object> response = new HashMap<>();
			response.put("code", ResponseCode.ERROR.getCode());
			response.put("message", companyEx.getMessage());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					 			 .body(response);
		}catch (Exception ex){
			// Retorna um erro genérico
			Map<String, Object> response = new HashMap<>();
			response.put("code", ResponseCode.UNEXPECTED_ERROR.getCode());
			response.put("message", ResponseMessage.UNEXPECTED_ERROR_MESSAGE.getMessage());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(response);
		} 
	}
}
