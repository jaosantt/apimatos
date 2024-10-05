package com.matosbrasil.api.enums;

import com.matosbrasil.api.util.FormatUtil;
import com.matosbrasil.api.util.ValidatorUtil;

public enum TypeCompany {
	CLIENT,          // CLIENTE
	SUPPLIER,       // FORNECEDOR
	CONSTRUCTION,  // OBRA
	SHIPPER;        // TRANSPORTADOR
	
    /**
     * Tenta converter um String para TypeCompany
     * @param input Entrada que será convertida
     * @return Retorna o TypeCompany
     */
    public static TypeCompany toTypeCompany(String input) {
    	String formattedValue = FormatUtil.formatNumber(input);
    	
    	if (!ValidatorUtil.isNumeric(formattedValue)) {
    	    return TypeCompany.valueOf(input.toUpperCase());
    	}
    	
    	Integer intValue = Integer.parseInt(formattedValue);

    	return TypeCompany.values()[intValue];
    }
}
