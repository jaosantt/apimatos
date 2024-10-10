package com.matosbrasil.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.matosbrasil.api.dto.AddressRequestDTO;
import com.matosbrasil.api.exception.CompanyException;
import com.matosbrasil.api.model.Address;
import com.matosbrasil.api.util.FormatUtil;
import com.matosbrasil.api.util.ValidatorUtil;



@Service
public class AddressService {
	
	public Address createAddress(AddressRequestDTO data){
		
		Address address = new Address();
		
		address.setPlace(data.place());
		address.setNumber(ValidatorUtil.isEmptyOrNullOrBlank(data.number()) ? null : data.number());
		address.setComplement(ValidatorUtil.isEmptyOrNullOrBlank(data.complement()) ? null : data.complement());
		
		// Valida o CEP informado
		address.setCep(FormatUtil.formatNumber(data.cep()));
		if(!ValidatorUtil.isNumeric(address.getCep()) || address.getCep().length() != 8){
			throw new CompanyException("O CEP está incorreto");
		}
		
		address.setDistrict(data.district());
		address.setCity(data.city());
		
		// Valida o estado informado
		address.setUf(data.uf().length() != 2 ? FormatUtil.getUFAbbreviation(data.uf()) : data.uf());
		if(!ValidatorUtil.isValidAbbreviationUf(address.getUf())) {
			throw new CompanyException("O UF informado é inválido.");
		}
		
		// Preenche a data de cadastro
		address.setDateCreated(new Date());
		
		return address;
	}
}