package com.matosbrasil.api.domain.company;

import java.util.Date;
import java.util.UUID;

import com.matosbrasil.api.domain.enums.TypeCompany;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAbstract {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "document")
	private String document; // CPF ou CNPJ
	
	@Column(name = "name")
	private String name; // Razão Social
	
	@Column(name = "fantasyName")
	private String fantasyName; // Nome Fantasia
	
	@Column(name = "stateRegistration")
	private String stateRegistration; // Inscrição Estadual
	
	@Column(name = "municipalRegistration")
	private String municipalRegistration; // Inscrição Municipal
	
	@Column(name = "phone")
	private String phone; // Telefone
	
	@Column(name = "email")
	private String email; // E-mail
	
	@Column(name = "dateCreated")
	private Date dateCreated; // Data Cadastro
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TypeCompany type;
	
	// Validação geral, com detecção de CPF ou CNPJ
	public boolean validateCPForCNPJ() {
	    if (this.getDocument() == null) {
	        return false;
	    }
	    
	    // Remove todos os caracteres que não sejam dígitos
	    String sanitizedDocument = this.getDocument().replaceAll("[^\\d]", ""); 

	    if (sanitizedDocument.length() == 11) {
	        return isValidCPF(sanitizedDocument);
	    } else if (sanitizedDocument.length() == 14) {
	        return isValidCNPJ(sanitizedDocument);
	    }

	    return false;
	}

	// Validação de CPF
	private boolean isValidCPF(String cpf) {
	    if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
	        return false; // Verifica se todos os dígitos são iguais
	    }

	    try {
	        int[] weightsCPF = {10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o primeiro dígito
	        int firstDigit = calculateDigit(cpf.substring(0, 9), weightsCPF);

	        int[] weightsCPFSecond = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o segundo dígito
	        int secondDigit = calculateDigit(cpf.substring(0, 9) + firstDigit, weightsCPFSecond);

	        return cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);
	    } catch (Exception e) {
	        return false;
	    }
	}

	// Validação de CNPJ
	private boolean isValidCNPJ(String cnpj) {
	    if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
	        return false; // Verifica se todos os dígitos são iguais
	    }

	    try {
	        int[] weightsCNPJFirst = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        int[] weightsCNPJSecond = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	        int firstDigit = calculateDigit(cnpj.substring(0, 12), weightsCNPJFirst);
	        int secondDigit = calculateDigit(cnpj.substring(0, 12) + firstDigit, weightsCNPJSecond);

	        return cnpj.equals(cnpj.substring(0, 12) + firstDigit + secondDigit);
	    } catch (Exception e) {
	        return false;
	    }
	}

	// Função genérica para calcular o dígito verificador
	private int calculateDigit(String str, int[] weights) {
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
}
