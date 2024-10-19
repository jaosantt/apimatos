package com.matosbrasil.api.dto;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(
		UUID id,
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
		@NotBlank(message = "É obrigatório informar o tipo da empresa.")
		String type,
		@Valid
		AddressRequestDTO address
) {

}
