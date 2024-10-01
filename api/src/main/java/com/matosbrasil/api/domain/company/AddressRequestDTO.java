package com.matosbrasil.api.domain.company;

public record AddressRequestDTO(
		String place,
		String number,
		String complement,
		String cep,
		String district,
		String city,
		String uf
) {

}
