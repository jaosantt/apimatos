package com.matosbrasil.api.util;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class FormatUtil {
	private static final Map<String, String> STATES = new HashMap<>();

    static {
    	/**
    	 * Mapeamento dos estados
    	 */
        STATES.put("AC", "Acre");
        STATES.put("AL", "Alagoas");
        STATES.put("AP", "Amapá");
        STATES.put("AM", "Amazonas");
        STATES.put("BA", "Bahia");
        STATES.put("CE", "Ceará");
        STATES.put("DF", "Federal District");
        STATES.put("ES", "Espírito Santo");
        STATES.put("GO", "Goiás");
        STATES.put("MA", "Maranhão");
        STATES.put("MT", "Mato Grosso");
        STATES.put("MS", "Mato Grosso do Sul");
        STATES.put("MG", "Minas Gerais");
        STATES.put("PA", "Pará");
        STATES.put("PB", "Paraíba");
        STATES.put("PR", "Paraná");
        STATES.put("PE", "Pernambuco");
        STATES.put("PI", "Piauí");
        STATES.put("RJ", "Rio de Janeiro");
        STATES.put("RN", "Rio Grande do Norte");
        STATES.put("RS", "Rio Grande do Sul");
        STATES.put("RO", "Rondônia");
        STATES.put("RR", "Roraima");
        STATES.put("SC", "Santa Catarina");
        STATES.put("SP", "São Paulo");
        STATES.put("SE", "Sergipe");
        STATES.put("TO", "Tocantins");
    }
    
    /**
     * Funcao responsavel por remover normalizar os caracteres
     * @param input entrada dos dados
     * @return
     */
    private static String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "");
    }
    
    /**
     * Função responsável por remover os caracteres especiais
     * @param input A entrada de dados que terá os caracteres especiais removidos
     * @return
     */
    public static String removeSpecialCharacters(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
        }

        // 1. Remover acentos usando Normalizer
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutAccents = normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // 2. Remover caracteres especiais, mantendo letras, números e espaços
        return withoutAccents.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
    /**
     * @param number o Número que será formatado
     * @return
     */
    public static String formatNumber(String number) {
    	
    	if (ValidatorUtil.isEmptyOrNullOrBlank(number)) {
    		return null;
    	}
    	
    	number = number.replaceAll("[^0-9]", "");
    	
    	return !ValidatorUtil.isEmptyOrNullOrBlank(number) ? number : null;
    }
    
    /**
     * Função responsável por remover os caracteres especiais do CPF ou do CNPJ
     * @param document O documento informado
     * @return
     */
    public static String formatCPForCNPJ(String document) {
    	return document != null ? document.replaceAll("[^\\d]", "") : null; 
    }
    /**
     * Funcao responsavel por traduzir a sigla para o nome do estado
     * @param abbreviation
     * @return
     */
    public static String getUFFullName(String abbreviation) {
        return STATES.getOrDefault(abbreviation.toUpperCase(), "Estado não encontrado");
    }
    /**
     * Funcao responsavel por traduzir o nome para a sigla do estado
     * @param fullName
     * @return
     */
    public static String getUFAbbreviation(String fullName) {
        String normalizedFullName = normalize(fullName);
        for (Map.Entry<String, String> entry : STATES.entrySet()) {
            if (normalize(entry.getValue()).equalsIgnoreCase(normalizedFullName)) {
                return entry.getKey();
            }
        }
        return "Sigla não encontrada";
    }
    /**
     * Pega o mapeamento dos estados
     * @return map dos estados (siglas, nomes)
     */
    public static Map<String, String> getStatesMap() {
    	return STATES;
    }
    
}
