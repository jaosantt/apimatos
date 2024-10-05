package com.matosbrasil.api.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ValidatorUtil {
	/**
	 * Funcao para verificar se o valor e numerico
	 * @param value valor que sera verificado
	 * @return true se o valor contem apenas numeros
	 */
	public boolean isNumeric(String value){
		/**
		 *Verifique se e vazio 
		 */
		if(value == null || value.isEmpty()){
			return false;
		}
		
		return value.matches("\\d+");
	}
	/**
	 * Função genérica para calcular o digito verificador
	 * @param str
	 * @param weights
	 * @return
	 */
	public int calculateDigitCpfOrCnpj(String str, int[] weights) {
	    int sum = 0;

	    for (int i = 0; i < str.length(); i++) {
	        int digit = Integer.parseInt(str.substring(i, i + 1));
	        sum += digit * weights[i];
	    }

	    int remainder = sum % 11;
	    if (remainder < 2) {
	        return 0;
	    } else {
	        return 11 - remainder;
	    }
	}
	/**
	 * Verifica se a validacao da UF e valida
	 * @param abbreviation
	 * @returna true se a sigla for correta e false se nao for
	 */
    public boolean isValidAbbreviationUf(String abbreviation){
    	Map<String, String> states = FormatUtil.getStatesMap();
    	return states.containsKey(abbreviation.toUpperCase());
    }

}
