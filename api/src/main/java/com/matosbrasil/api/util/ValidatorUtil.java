package com.matosbrasil.api.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.matosbrasil.api.enums.TypeCompany;

@Component
public class ValidatorUtil {
	/**
	 * Funcao para verificar se o valor e numerico
	 * @param value valor que sera verificado
	 * @return true se o valor contem apenas numeros
	 */
	public static boolean isNumeric(String value){
		/**
		 *Verifique se e vazio 
		 */
		if(isEmptyOrNullOrBlank(value)){
			return false;
		}
		
		return value.matches("\\d+");
	}
	
	/**
	 * Validação de CPF
	 * 
	 * @param cpf A String do CPF sem os pontos
	 * @return retorna true se o Cpf for valido e false se nao for
	 */
	private static boolean isValidCPF(String cpf) {
	    if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
	        return false; // Verifica se todos os dígitos são iguais
	    }

	    try {
	        int[] weightsCPF = {10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o primeiro dígito
	        int firstDigit = calculateDigitCpfOrCnpj(cpf.substring(0, 9), weightsCPF);

	        int[] weightsCPFSecond = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o segundo dígito
	        int secondDigit = calculateDigitCpfOrCnpj(cpf.substring(0, 9) + firstDigit, weightsCPFSecond);

	        return cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);
	    } catch (Exception e) {
	        return false;
	    }
	}

	/**
	 * Validacao de CNPJ
	 * @param cnpj A String CNPJ sem pontos
	 * @return true se o cnpj for valido e false se nao for
	 */
	// Validação de CNPJ
	private static boolean isValidCNPJ(String cnpj) {
	    if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
	        return false; // Verifica se todos os dígitos são iguais
	    }

	    try {
	        int[] weightsCNPJFirst = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        int[] weightsCNPJSecond = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	        int firstDigit = calculateDigitCpfOrCnpj(cnpj.substring(0, 12), weightsCNPJFirst);
	        int secondDigit = calculateDigitCpfOrCnpj(cnpj.substring(0, 12) + firstDigit, weightsCNPJSecond);

	        return cnpj.equals(cnpj.substring(0, 12) + firstDigit + secondDigit);
	    } catch (Exception e) {
	        return false;
	    }
	}
	
    /**
     * Verifica se o atributo é vazio ou nulo ou em branco
     * @param input Entrada que será verificada
     * @return
     */
    public static boolean isEmptyOrNullOrBlank(String input) {
    	if (input == null || input.isBlank() || input.isEmpty()) {
    		return true;
    	}
    	
    	return false;
    }
    
    /***
     * Verifica se o tipo de empresa informado existe
     * @param value Valor que será verificado
     * @return
     */
    public static boolean isValidTypeCompany(String value) {
    	if (isEmptyOrNullOrBlank(value)) {
    		return false;
    	}
    	
    	String formattedValue = FormatUtil.formatNumber(value);
    	
    	if (!isNumeric(formattedValue)) {
    	    for (TypeCompany type : TypeCompany.values()) {
    	        if (type.name().equalsIgnoreCase(value)) {
    	            return true;
    	        }
    	    }
    	    return false;
    	}
    	
    	Integer intValue = Integer.parseInt(formattedValue);
    	
    	return intValue >= 0 && intValue < TypeCompany.values().length;
    }
	
	/**
	 * Verifica se a validacao da UF e valida
	 * @param abbreviation
	 * @returna true se a sigla for correta e false se nao for
	 */
    public static boolean isValidAbbreviationUf(String abbreviation){
    	Map<String, String> states = FormatUtil.getStatesMap();
    	return states.containsKey(abbreviation.toUpperCase());
    }

	/**
	 * Função genérica para calcular o digito verificador
	 * @param str
	 * @param weights
	 * @return
	 */
	private static int calculateDigitCpfOrCnpj(String str, int[] weights) {
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
	 * Validação geral, com detecção de CPF ou CNPJ
	 * 
	 * @param document Documento que será validado
	 * 
	 * @return true se o documento for valido e false se nao for
	 */
	public static boolean validateCPForCNPJ(String document) {
	    if (document == null) {
	        return false;
	    }
	    
	    // Remove todos os caracteres que não sejam dígitos
	    String sanitizedDocument = FormatUtil.formatCPForCNPJ(document); 

	    if (sanitizedDocument.length() == 11) {
	        return isValidCPF(sanitizedDocument);
	    } else if (sanitizedDocument.length() == 14) {
	        return isValidCNPJ(sanitizedDocument);
	    }

	    return false;
	}
}
