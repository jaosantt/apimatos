package com.matosbrasil.api.domain.company;

import com.matosbrasil.api.domain.enums.TypeCompany;

public record CompanyRequestDTO(
		String document,
		String name,
		String fantasyName,
		String stateRegistration,
		String municipalRegistration,
		String phone,
		String email, 
		TypeCompany type,
		AddressRequestDTO address
) {

}
