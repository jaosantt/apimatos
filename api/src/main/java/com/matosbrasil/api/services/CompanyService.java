package com.matosbrasil.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.domain.address.Address;
import com.matosbrasil.api.domain.company.Company;
import com.matosbrasil.api.domain.company.CompanyRequestDTO;
import com.matosbrasil.api.exceptions.CompanyException;
import com.matosbrasil.api.repositories.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
	@Autowired
	private AddressService addressService;
	
	public Company createCompany(CompanyRequestDTO data) {
		
		try {
			Company company = new Company();
			company.setDocument(data.document());
			// Valida o CPF informado
			if (!company.validateCPForCNPJ()) {
				// Lança uma exceção
				throw new CompanyException("O CPF/CNPJ informado é inválido.");
			}
			company.setName(data.name());
			company.setFantasyName(data.fantasyName());
			company.setStateRegistration(data.stateRegistration());
			company.setMunicipalRegistration(data.municipalRegistration());
			company.setPhone(data.phone());
			company.setEmail(data.email());
			company.setType(data.type());
			company.setDateCreated(new Date());
			
			Address address = addressService.createAddress(data.address());
			
			company.setAddress(address);
			repository.save(company);
			
			return company;
		} catch (CompanyException companyEx) {
			throw companyEx;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
