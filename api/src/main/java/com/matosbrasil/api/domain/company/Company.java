package com.matosbrasil.api.domain.company;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cadempresa")
@Getter
@Setter
public class Company extends CompanyAbstract{
	
}
