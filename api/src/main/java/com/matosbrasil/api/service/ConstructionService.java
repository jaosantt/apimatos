package com.matosbrasil.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.dto.ConstructionRequestDTO;
import com.matosbrasil.api.enums.TypeCompany;
import com.matosbrasil.api.exception.CompanyException;
import com.matosbrasil.api.model.Address;
import com.matosbrasil.api.model.Construction;
import com.matosbrasil.api.repository.ConstructionRepository;
import com.matosbrasil.api.util.FormatUtil;
import com.matosbrasil.api.util.ValidatorUtil;

@Service
public class ConstructionService {

	@Autowired
	private ConstructionRepository repository;
	@Autowired
	private AddressService addressService;
	
	public void createConstruction(ConstructionRequestDTO data) {
		
		try {
			Construction construction = new Construction();
			
			// Valida o documento informado
			if (!ValidatorUtil.validateCPForCNPJ(data.document())) {
				// Lança uma exceção
				throw new CompanyException("O CPF/CNPJ informado é inválido.");
			}
			// Formata o documento e salva no banco de dados
			construction.setDocument(FormatUtil.formatCPForCNPJ(data.document()));

			// Seta os demais campos
			construction.setName(FormatUtil.removeSpecialCharacters(data.name()));
			construction.setFantasyName(FormatUtil.removeSpecialCharacters(data.fantasyName()));
			
			// Valida se a inscrição estadual é vazia ou nula, caso não seja verifica se é um número válido
			if(
				!ValidatorUtil.isEmptyOrNullOrBlank(FormatUtil.formatNumber(data.stateRegistration())) && 
				!ValidatorUtil.isNumeric(FormatUtil.formatNumber(data.stateRegistration()))
			){
				throw new CompanyException("A Inscrição Estadual não é válida");
			}
			construction.setStateRegistration(FormatUtil.formatNumber(data.stateRegistration()));

			// Valida se a inscrição muncipal só contém números
			if(
				!ValidatorUtil.isEmptyOrNullOrBlank(FormatUtil.formatNumber(data.municipalRegistration())) && 
				!ValidatorUtil.isNumeric(FormatUtil.formatNumber(data.municipalRegistration()))
			) {
				throw new CompanyException("A Inscrição Municipal não é válida");
			}
			construction.setMunicipalRegistration(FormatUtil.formatNumber(data.municipalRegistration()));
			
			// Valida se o telefone só contém números
			if(
				!ValidatorUtil.isEmptyOrNullOrBlank(FormatUtil.formatNumber(data.phone())) &&
				!ValidatorUtil.isNumeric(FormatUtil.formatNumber(data.phone()))
			) {
				throw new CompanyException("O número de telefone é inválido");
			}
			construction.setPhone(FormatUtil.formatNumber(data.phone()));
			
			// Seta o e-mail
			construction.setEmail(!ValidatorUtil.isEmptyOrNullOrBlank(data.email()) ? data.email() : null);
						
			// Seta o tipo obra
			construction.setType(TypeCompany.CONSTRUCTION);
			
			// Verifica se a empresa existe
			if (repository.existsByDocument(construction.getDocument())) {
				Construction existingConstruction = repository.getByDocument(construction.getDocument());
				if (existingConstruction.getType().equals(construction.getType())) {
					throw new CompanyException("A obra informada já existe.");
				}
			}
			
			// Gera data de criação
			construction.setDateCreated(new Date());
			
			// Preenche os dados de endereço
			Address address = addressService.createAddress(data.address());
			construction.setAddress(address);
			
			// Salva no banco de dados
			repository.save(construction);
			
		}catch (CompanyException companyEx) {
			throw companyEx;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
