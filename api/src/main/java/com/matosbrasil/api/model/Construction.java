package com.matosbrasil.api.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
		name = "cadobra", 
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"cadobrdocumento", "cadobrtipo"})}
		)
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "cadobrid"))
@AttributeOverride(name = "document", column = @Column(name = "cadobrdocumento"))
@AttributeOverride(name = "name", column = @Column(name = "cadobrrazaosocial"))
@AttributeOverride(name = "fantasyName", column = @Column(name = "cadobrnomefantasia"))
@AttributeOverride(name = "stateRegistration", column = @Column(name = "cadobrinscricaoestadual"))
@AttributeOverride(name = "municipalRegistration", column = @Column(name = "cadobrinscricaomunicpal"))
@AttributeOverride(name = "phone", column = @Column(name = "cadobrtelefone"))
@AttributeOverride(name = "email", column = @Column(name = "cadobremail"))
@AttributeOverride(name = "dateCreated", column = @Column(name = "cadobrdtcadastro"))
@AttributeOverride(name = "lastModified", column = @Column(name = "cadobrdtatualizacao"))
@AttributeOverride(name = "type", column = @Column(name = "cadobrtipo"))
public class Construction extends CompanyAbstract{
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cadobrendereco")
	private Address address; // Endereço
}

