package com.matosbrasil.api.domain.company;

import com.matosbrasil.api.domain.enums.TypeShipping;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cadtransportador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipper extends CompanyAbstract {
	private String       cnh;      /// Carteira Nacional de Habilitação
	private String       antt;     /// ANTT
	
	@Enumerated(EnumType.STRING)
	private TypeShipping typeShip; /// Tipo do Transportador (1. TAC, 2. ETC, 3. CTC)
}