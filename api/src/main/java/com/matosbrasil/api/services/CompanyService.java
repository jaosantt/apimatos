package com.matosbrasil.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.domain.company.Address;
import com.matosbrasil.api.domain.company.Company;
import com.matosbrasil.api.domain.company.CompanyRequestDTO;
import com.matosbrasil.api.repositories.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
	
	public Company createCompany(CompanyRequestDTO data) {
		
		Company company = new Company();
		company.setDocument(data.document());
		company.setName(data.name());
		company.setFantasyName(data.fantasyName());
		company.setStateRegistration(data.stateRegistration());
		company.setMunicipalRegistration(data.municipalRegistration());
		company.setPhone(data.phone());
		company.setEmail(data.email());
		company.setType(data.type());
		company.setDateCreated(new Date());
		
		Address address = new Address();
		address.setPlace(data.address().place());
		address.setNumber(data.address().number());
		address.setComplement(data.address().complement());
		address.setCep(data.address().cep());
		address.setDistrict(data.address().district());
		address.setCity(data.address().city());
		address.setUf(data.address().uf());
		address.setDateCreated(new Date());
		
		company.setAddress(address);
		repository.save(company);
		
		return company;
	}
	
}
