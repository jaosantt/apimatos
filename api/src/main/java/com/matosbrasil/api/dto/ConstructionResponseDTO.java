package com.matosbrasil.api.dto;

import java.util.UUID;

public record ConstructionResponseDTO(		
		UUID id,
		String document,
		String name,
		String fantasyName,
		String stateRegistration,
		String municipalRegistration,
		String phone,
		String email, 
		AddressResponseDTO address
) {}
