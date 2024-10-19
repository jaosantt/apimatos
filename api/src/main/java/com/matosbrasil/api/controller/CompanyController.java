package com.matosbrasil.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matosbrasil.api.dto.CompanyRequestDTO;
import com.matosbrasil.api.dto.CompanyResponseDTO;
import com.matosbrasil.api.enums.ResponseCode;
import com.matosbrasil.api.enums.ResponseMessage;
import com.matosbrasil.api.exception.CompanyException;
import com.matosbrasil.api.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController extends BaseController{
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> create (@Valid @RequestBody CompanyRequestDTO body){
		try {
			// Tenta criar a empresa
			this.companyService.createCompany(body);
			
			// Retorno uma resposta com sucesso
			return createSuccessResponse(ResponseMessage.SUCCESSFUL_REGISTRATION);
		} catch (CompanyException companyEx) { 
			// Retorna uma resposta de erro tratado
			return createErrorResponse(
						HttpStatus.INTERNAL_SERVER_ERROR,
						ResponseCode.ERROR.getCode(), 
						companyEx.getMessage()
				   );
		} catch (Exception ex){
			// Retorna um erro genérico
			return createErrorResponse(
						HttpStatus.INTERNAL_SERVER_ERROR,
						ResponseCode.UNEXPECTED_ERROR.getCode(), 
						ResponseMessage.UNEXPECTED_ERROR_MESSAGE.getMessage()
				   );
		} 
	}
	
	@GetMapping
	public ResponseEntity<List<CompanyResponseDTO>> getCompanys(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		List<CompanyResponseDTO> allCompanys = this.companyService.getCompanys(page, size);
		return ResponseEntity.ok(allCompanys);
		 
	}
}
