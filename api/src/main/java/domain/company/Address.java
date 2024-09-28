package domain.company;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cadempresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue
	private UUID    id;           // ID do Endereço	
	private String  place;        // Logradouro
	private String  number;       // Número do Endereço
	private String  complement;   // Complemento
	private String  cep;          // CEP
	private String  district;     // Bairro
	private String  city;         // Cidade
	private String  uf;           // Estado
}
