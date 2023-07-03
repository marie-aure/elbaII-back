package fr.liza.elba.model.jpa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tour {

	@Id
	@GeneratedValue
	private Long id;
	private Long numero;
	private boolean actif = false;

	@OneToOne(cascade = CascadeType.MERGE)
	private Famille famille;


}
