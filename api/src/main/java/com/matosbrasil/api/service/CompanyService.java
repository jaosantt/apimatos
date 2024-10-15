package com.matosbrasil.api.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.dto.AddressResponseDTO;
import com.matosbrasil.api.dto.CompanyRequestDTO;
import com.matosbrasil.api.dto.CompanyResponseDTO;
import com.matosbrasil.api.enums.TypeCompany;
import com.matosbrasil.api.exception.CompanyException;
import com.matosbrasil.api.model.Address;
import com.matosbrasil.api.model.Company;
import com.matosbrasil.api.repository.CompanyRepository;
import com.matosbrasil.api.util.FormatUtil;
import com.matosbrasil.api.util.ValidatorUtil;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
	@Autowired
	private AddressService addressService;
	
	/**
	 * Função responsável por efetuar o cadastro de uma empresa
	 * @param data
	 */
	public void createCompany(CompanyRequestDTO data) {
		
		try {
			Company company = new Company();
			
			// Valida o documento informado
			if (!ValidatorUtil.validateCPForCNPJ(data.document())) {
				// Lança uma exceção
				throw new CompanyException("O CPF/CNPJ informado é inválido.");
			}
			// Formata o documento e salva no banco de dados
			company.setDocument(FormatUtil.formatCPForCNPJ(data.document()));
			
			// Seta os demais campos
			company.setName(FormatUtil.removeSpecialCharacters(data.name()));
			company.setFantasyName(FormatUtil.removeSpecialCharacters(data.fantasyName()));
			
			// Valida se a inscrição estadual é vazia ou nula, caso não seja verifica se é um número válido
			if(
				!ValidatorUtil.isEmptyOrNullOrBlank(FormatUtil.formatNumber(data.stateRegistration())) && 
				!ValidatorUtil.isNumeric(FormatUtil.formatNumber(data.stateRegistration()))
			){
				throw new CompanyException("A Inscrição Estadual não é válida");
			}
			company.setStateRegistration(FormatUtil.formatNumber(data.stateRegistration()));
			
			// Valida se a inscrição muncipal só contém números
			if(
				!ValidatorUtil.isEmptyOrNullOrBlank(FormatUtil.formatNumber(data.municipalRegistration())) && 
				!ValidatorUtil.isNumeric(FormatUtil.formatNumber(data.municipalRegistration()))
			) {
				throw new CompanyException("A Inscrição Municipal não é válida");
			}
			company.setMunicipalRegistration(FormatUtil.formatNumber(data.municipalRegistration()));
			
			// Valida se o telefone só contém números
			if(
				!ValidatorUtil.isEmptyOrNullOrBlank(FormatUtil.formatNumber(data.phone())) &&
				!ValidatorUtil.isNumeric(FormatUtil.formatNumber(data.phone()))
			) {
				throw new CompanyException("O número de telefone é inválido");
			}
			company.setPhone(FormatUtil.formatNumber(data.phone()));

			// Seta o e-mail
			company.setEmail(!ValidatorUtil.isEmptyOrNullOrBlank(data.email()) ? data.email() : null);
			
			// Valida o tipo de empresa
			if (!ValidatorUtil.isValidTypeCompany(data.type())) {
				throw new CompanyException("O tipo de empresa informado não é válido.");
			}
			
			company.setType(TypeCompany.toTypeCompany(data.type()));
			if(!company.isClient() && !company.isSupplier()) {
				throw new CompanyException("O tipo de empresa informado não está disponível.");
			}
			
			// Verifica se a empresa existe
			if (repository.existsByDocument(company.getDocument())) {
				Company existingCompany = repository.getByDocument(company.getDocument());
				if (existingCompany.getType().equals(company.getType())) {
					throw new CompanyException("A empresa informada já existe.");
				}
			}
			
			// Gera data de criação
			company.setDateCreated(new Date());
			
			// Preenche os dados de endereço
			Address address = addressService.createAddress(data.address());
			company.setAddress(address);
			
			// Salva no banco de dados
			repository.save(company);
		} catch (CompanyException companyEx) {
			throw companyEx;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * Busca as empresas com paginação
	 * @param page Número da página
	 * @param size Tamanho da página
	 * @return Retorna uma lista de empresas
	 */
	public List<CompanyResponseDTO> getCompanys(int page, int size) {	
		// Valida o size e o page
		if (size < 0 || page < 0) {
			throw new CompanyException("Parâmetros de busca inválidos.");
		}
		
		// Valida o valor máximo do size
		if (size > 500) {
			throw new CompanyException("Tamanho de busca superior ao permitido");
		}
		
		// Cria o objeto de paginação
		Pageable pageable = PageRequest.of(page,size);
		
		// Busca os registros no banco de dados
		Page<Company> companyPage = this.repository.findAll(pageable);
		
		// Retorna um JSON com as empresas 
	    return companyPage.stream()
	            .map(company -> {
	            	// Tenta construir o JSON
	                try {
	                    AddressResponseDTO address = addressService.getAddressById(company.getAddress().getId());
	                    return new CompanyResponseDTO(
	                            company.getId(),
	                            company.getDocument(),
	                            company.getName(),
	                            company.getFantasyName(),
	                            company.getStateRegistration(),
	                            company.getMunicipalRegistration(),
	                            company.getPhone(),
	                            company.getEmail(),
	                            company.getType().ordinal(),
	                            address
	                    );
	                } catch (Exception e) {
	                	// Caso ocorra algum erro salva registro como vazio
	                    return null; 
	                }
	            })
	            .filter(Objects::nonNull) // Filtra para que a lista retorne apenas registros preenchidos ou seja "NOT NULLS"
	            .toList(); 
	}
}
