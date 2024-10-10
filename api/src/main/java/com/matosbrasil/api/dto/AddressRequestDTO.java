package com.matosbrasil.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
		@NotBlank(message = "Logradouro obrigatório")
		String place,
		String number,
		String complement,
		@NotBlank(message = "CEP obrigatório")
		String cep,
		@NotBlank(message = "Bairro obrigatório")
		String district,
		@NotBlank(message = "A cidade é obrigatória")
		String city,
		@NotBlank(message = "O estado e obrigatório")
		String uf
) {

}
