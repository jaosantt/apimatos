package com.matosbrasil.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.domain.address.Address;
import com.matosbrasil.api.domain.address.AddressRequestDTO;
import com.matosbrasil.api.exceptions.CompanyException;
import com.matosbrasil.api.utils.FormatUtil;
import com.matosbrasil.api.utils.ValidatorUtil;



@Service
public class AddressService {
	
	@Autowired
	private ValidatorUtil validatorUtil;
	
	public Address createAddress(AddressRequestDTO data){
		
		Address address = new Address();
		
		address.setPlace(data.place());
		address.setNumber(data.number());
		address.setComplement(data.complement());
		
		// Valida o CEP informado
		address.setCep(FormatUtil.formatNumber(data.cep()));
		if(!validatorUtil.isNumeric(address.getCep()) || address.getCep().length() != 8){
			throw new CompanyException("O CEP está incorreto");
		}
		
		address.setDistrict(data.district());
		address.setCity(data.city());
		
		// Valida o estado informado
		address.setUf(data.uf().length() != 2 ? FormatUtil.getUFAbbreviation(data.uf()) : data.uf());
		if(!validatorUtil.isValidAbbreviationUf(address.getUf())) {
			throw new CompanyException("O UF informado é inválido.");
		}
		
		// Preenche a data de cadastro
		address.setDateCreated(new Date());
		
		return address;
	}
}