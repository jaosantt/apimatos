package com.matosbrasil.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matosbrasil.api.domain.company.Company;
import com.matosbrasil.api.domain.company.CompanyRequestDTO;
import com.matosbrasil.api.services.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<Company> create (@RequestBody CompanyRequestDTO body){
		Company newCompany = this.companyService.createCompany(body);
		return ResponseEntity.ok(newCompany);
	}
}
