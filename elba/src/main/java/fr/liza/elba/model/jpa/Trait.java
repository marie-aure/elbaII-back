package fr.liza.elba.model.jpa;

import fr.liza.elba.model.enumeration.TypeTrait;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Trait {

	@Id
	@GeneratedValue
	private int id;

	private String libelle;
	@Enumerated(EnumType.STRING)
	private TypeTrait type;

}
