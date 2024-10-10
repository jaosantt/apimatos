package com.matosbrasil.api.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cadempresa")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "cadempid"))
@AttributeOverride(name = "document", column = @Column(name = "cadempdocumento"))
@AttributeOverride(name = "name", column = @Column(name = "cademprazaosocial"))
@AttributeOverride(name = "fantasyName", column = @Column(name = "cadempnomefantasia"))
@AttributeOverride(name = "stateRegistration", column = @Column(name = "cadempinscricaoestadual"))
@AttributeOverride(name = "municipalRegistration", column = @Column(name = "cadempinscricaomunicpal"))
@AttributeOverride(name = "phone", column = @Column(name = "cademptelefone"))
@AttributeOverride(name = "email", column = @Column(name = "cadempemail"))
@AttributeOverride(name = "dateCreated", column = @Column(name = "cadempdtcadastro"))
@AttributeOverride(name = "type", column = @Column(name = "cademptipo"))
public class Company extends CompanyAbstract{
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cadempendereco")
	private Address address; // Endereço
}
