package com.matosbrasil.api.domain.address;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cadendereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue
    @Column(name = "cadendid")
	private UUID    id;           // ID do Endereço	

    @Column(name = "cadendlogradouro")
    private String place; // Logradouro

    @Column(name = "cadendnumero")
    private String number; // Número do Endereço

    @Column(name = "cadendcomplemento")
    private String complement; // Complemento

    @Column(name = "cadendcep")
    private String cep; // CEP

    @Column(name = "cadendbairro")
    private String district; // Bairro

    @Column(name = "cadendmunicipio")
    private String city; // Cidade

    @Column(name = "cadenduf")
    private String uf; // Estado
    
	@Column(name = "cadenddtcadastro")
	private Date dateCreated; // Data Cadastro
}
