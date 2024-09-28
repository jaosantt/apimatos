package domain.company;

import java.util.UUID;

import domain.company.enums.TypeCompany;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
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
	private UUID id;
	
	private String      document;              // CPF ou CNPJ
	private String      name;                  // Razão Social
	private String      fantasyName;           // Nome Fantasia
	private String      stateRegistration;     // Inscrição Estadual
	private String      municipalRegistration; // Inscrição Municipal
	private String      phone;                 // Telefone
	private String      email;				   // E-mail
	
	@OneToOne
    @JoinColumn(name = "cadempendereco")
	private Address		address;               // Endereço
	
	@Enumerated(EnumType.STRING)
	private TypeCompany type;
}
