package com.matosbrasil.api.domain.company;

import com.matosbrasil.api.domain.address.AddressRequestDTO;
import com.matosbrasil.api.domain.enums.TypeCompany;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(
		@NotBlank(message = "O Documento é obrigatório")
		String document,
		@NotBlank(message = "A Razão Social é obrigatória")
		String name,
		String fantasyName,
		String stateRegistration,
		String municipalRegistration,
		String phone,
		@Email(message = "O E-mail fornecido não e válido")
		String email, 
		@NotBlank(message = "O tipo não foi informado")
		TypeCompany type,
		@NotBlank(message = "O endereço não foi informado")
		AddressRequestDTO address
) {

}
