package com.matosbrasil.api.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.matosbrasil.api.domain.company.Address;
import com.matosbrasil.api.domain.company.AddressRequestDTO;

@Service
public class AddressService {
	
	public Address createAddress(AddressRequestDTO data){
		
		Address address = new Address();
		address.setPlace(data.place());
		address.setNumber(data.number());
		address.setComplement(data.complement());
		address.setCep(data.cep());
		address.setDistrict(data.district());
		address.setCity(data.city());
		address.setUf(data.uf());
		address.setDateCreated(new Date());
		
		return address;
	}
}