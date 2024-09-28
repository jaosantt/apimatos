package domain.company;

import jakarta.persistence.Entity;
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
public class Company extends CompanyAbstract{
	
}
