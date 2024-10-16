package com.matosbrasil.api.model;

import java.util.Date;
import java.util.UUID;

import com.matosbrasil.api.enums.TypeCompany;

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
	/**
	 * Verifica se e uma obra
	 * @return true se a obra for valida e false se nao for
	 */
	public boolean isConstruction() {
    	return this.getType() == TypeCompany.CONSTRUCTION;
    }
}
