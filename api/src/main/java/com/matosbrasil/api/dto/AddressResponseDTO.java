package com.matosbrasil.api.dto;

public record AddressResponseDTO(	
		String place,
		String number,
		String complement,
		String cep,
		String district,
		String city,
		String uf
) {}
