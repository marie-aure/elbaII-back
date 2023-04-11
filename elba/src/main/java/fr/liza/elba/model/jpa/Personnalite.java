package fr.liza.elba.model.jpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Personnalite {

	@ManyToOne // pas de liaison retour
	private Souhait souhait;

	@ManyToOne // pas de liaison retour
	private Trait traitMental;

	@ManyToOne // pas de liaison retour
	private Trait traitPhysique;

	@ManyToOne // pas de liaison retour
	private Trait traitSocial;

	@ManyToOne // pas de liaison retour
	private Trait traitStyleVie;

	@ManyToOne // pas de liaison retour
	private Trait traitBonus;
}
