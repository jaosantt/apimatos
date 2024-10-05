package com.matosbrasil.api.domain.company;

import com.matosbrasil.api.domain.address.AddressRequestDTO;
import com.matosbrasil.api.domain.enums.TypeCompany;

import jakarta.validation.constraints.Email;

public record CompanyRequestDTO(
		String document,
		String name,
		String fantasyName,
		String stateRegistration,
		String municipalRegistration,
		String phone,
		@Email(message = "O E-mail fornecido não e válido")
		String email, 
		TypeCompany type,
		AddressRequestDTO address
) {

}
