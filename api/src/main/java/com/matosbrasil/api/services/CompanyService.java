package com.matosbrasil.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.domain.address.Address;
import com.matosbrasil.api.domain.company.Company;
import com.matosbrasil.api.domain.company.CompanyRequestDTO;
import com.matosbrasil.api.exceptions.CompanyException;
import com.matosbrasil.api.repositories.CompanyRepository;
import com.matosbrasil.api.utils.ValidatorUtil;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ValidatorUtil validatorUtil;
	
	public Company createCompany(CompanyRequestDTO data) {
		
		try {
			Company company = new Company();
			company.setDocument(data.document());
			/**
			 * Valida o CPF informado
			 */
			if (!company.validateCPForCNPJ()) {
				/**
				 * lanca uma ececao
				 */
				throw new CompanyException("O CPF/CNPJ informado é inválido.");
			}
			company.setName(data.name());
			company.setFantasyName(data.fantasyName());
			company.setStateRegistration(data.stateRegistration());
			if(validatorUtil.isNumeric(company.getStateRegistration())){
				throw new CompanyException("A Inscrição Estadual nâo é válida");
			}
			
			company.setMunicipalRegistration(data.municipalRegistration());
			if(validatorUtil.isNumeric(company.getMunicipalRegistration())) {
				throw new CompanyException("A Inscrição Municipal nâo é válida");
			}
			
			company.setPhone(data.phone());
			if(validatorUtil.isNumeric(company.getPhone())){
				throw new CompanyException("O número de telefone e inválido");
			}
			
			company.setEmail(data.email());
			
			company.setType(data.type());
			if(!company.isClient() && !company.isSupplier()) {
				throw new CompanyException("O tipo de empresa informado e inválido");
			}
			
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
