package com.matosbrasil.api.domain.company;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.matosbrasil.api.domain.enums.TypeCompany;
import com.matosbrasil.api.utils.ValidatorUtil;

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
	@Autowired
	private ValidatorUtil validatorUtil;
	
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
	
	/**
	 * Validação geral, com detecção de CPF ou CNPJ
	 * @return true se o documento for valido e false se nao for
	 */
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

	/**
	 * Validação de CPF
	 * 
	 * @param cpf A String do CPF sem os pontos
	 * @return retorna true se o Cpf for valido e false se nao for
	 */
	private boolean isValidCPF(String cpf) {
	    if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
	        return false; // Verifica se todos os dígitos são iguais
	    }

	    try {
	        int[] weightsCPF = {10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o primeiro dígito
	        int firstDigit = validatorUtil.calculateDigitCpfOrCnpj(cpf.substring(0, 9), weightsCPF);

	        int[] weightsCPFSecond = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o segundo dígito
	        int secondDigit = validatorUtil.calculateDigitCpfOrCnpj(cpf.substring(0, 9) + firstDigit, weightsCPFSecond);

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
	private boolean isValidCNPJ(String cnpj) {
	    if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
	        return false; // Verifica se todos os dígitos são iguais
	    }

	    try {
	        int[] weightsCNPJFirst = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        int[] weightsCNPJSecond = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	        int firstDigit = validatorUtil.calculateDigitCpfOrCnpj(cnpj.substring(0, 12), weightsCNPJFirst);
	        int secondDigit = validatorUtil.calculateDigitCpfOrCnpj(cnpj.substring(0, 12) + firstDigit, weightsCNPJSecond);

	        return cnpj.equals(cnpj.substring(0, 12) + firstDigit + secondDigit);
	    } catch (Exception e) {
	        return false;
	    }
	}
	/**
	 * Verifica se e um fornecedor
	 * @return true se o fornecedor for valido e false se nao for
	 */
	public boolean isSupplier() {
    	return this.getType() == TypeCompany.SUPPLIER;
    }
	/**
	 * Verifica se e um client
	 * @return true se o cliente for valido e false se nao for
	 */
	public boolean isClient() {
    	return this.getType() == TypeCompany.CLIENT;
    }
}
