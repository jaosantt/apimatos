package com.matosbrasil.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.domain.address.Address;
import com.matosbrasil.api.domain.company.Company;
import com.matosbrasil.api.domain.company.CompanyRequestDTO;
import com.matosbrasil.api.exceptions.CompanyException;
import com.matosbrasil.api.repositories.CompanyRepository;
import com.matosbrasil.api.utils.FormatUtil;
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
			
			// Valida o documento informado
			if (!validatorUtil.validateCPForCNPJ(data.document())) {
				// Lança uma exceção
				throw new CompanyException("O CPF/CNPJ informado é inválido.");
			}
			// Formata o documento e salva no banco de dados
			company.setDocument(FormatUtil.formatCPForCNPJ(data.document()));
			
			// Seta os demais campos
			company.setName(FormatUtil.removeSpecialCharacters(data.name()));
			company.setFantasyName(FormatUtil.removeSpecialCharacters(data.fantasyName()));
			
			// Valida se a inscrição estadual só contem números
			company.setStateRegistration(FormatUtil.formatNumber(data.stateRegistration()));
			if(!validatorUtil.isNumeric(company.getStateRegistration())){
				throw new CompanyException("A Inscrição Estadual não é válida");
			}
			
			// Valida se a inscrição muncipal só contém números
			company.setMunicipalRegistration(FormatUtil.formatNumber(data.municipalRegistration()));
			if(!validatorUtil.isNumeric(company.getMunicipalRegistration())) {
				throw new CompanyException("A Inscrição Municipal não é válida");
			}
			
			// Valida se o telefone só contém números
			company.setPhone(FormatUtil.formatNumber(data.phone()));
			if(!validatorUtil.isNumeric(company.getPhone())){
				throw new CompanyException("O número de telefone é inválido");
			}
			
			company.setEmail(data.email());
			
			// Valida se o tipo de empresa informado é válido
			company.setType(data.type());
			if(!company.isClient() && !company.isSupplier()) {
				throw new CompanyException("O tipo de empresa informado é inválido");
			}
			
			// Gera data de criação
			company.setDateCreated(new Date());
			
			// Preenche os dados de endereço
			Address address = addressService.createAddress(data.address());
			company.setAddress(address);
			
			// Salva no banco de dados
			repository.save(company);
			
			return company;
		} catch (CompanyException companyEx) {
			throw companyEx;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
