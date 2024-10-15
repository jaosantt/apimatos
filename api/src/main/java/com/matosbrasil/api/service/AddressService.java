package com.matosbrasil.api.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matosbrasil.api.dto.AddressRequestDTO;
import com.matosbrasil.api.dto.AddressResponseDTO;
import com.matosbrasil.api.exception.CompanyException;
import com.matosbrasil.api.model.Address;
import com.matosbrasil.api.repository.AddressRepository;
import com.matosbrasil.api.util.FormatUtil;
import com.matosbrasil.api.util.ValidatorUtil;



@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;
	
	
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
	
	/**
	 * Busca o endereço pelo UUID e retorna com um ResponseDTO
	 * @param id
	 * @return Retorna um ResponseDTO
	 * @throws Exception
	 */
	public AddressResponseDTO getAddressById(UUID id) throws Exception {
		Optional<Address> optAddress = repository.findById(id);
		
		// Verifica se retornou algo da busca
		if (!optAddress.isPresent()) {
			throw new Exception("Empresa cadastrada sem endereço válido.");
		}
		
		return new AddressResponseDTO(
					optAddress.get().getPlace(),
					optAddress.get().getNumber(),
					optAddress.get().getComplement(),
					optAddress.get().getCep(),
					optAddress.get().getDistrict(),
					optAddress.get().getCity(),
					optAddress.get().getUf()
				);
	}
}