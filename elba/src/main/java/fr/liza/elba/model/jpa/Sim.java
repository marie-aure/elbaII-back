package fr.liza.elba.model.jpa;

import fr.liza.elba.model.enumeration.Espece;
import fr.liza.elba.model.enumeration.Genre;
import fr.liza.elba.model.enumeration.Orientation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sim {

	@Id
	@GeneratedValue
	private Long id;

	private String prenom;
	private String nom;
	private String nomOrigine;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	@Enumerated(EnumType.STRING)
	private Espece espece;
	@Enumerated(EnumType.STRING)
	private Orientation orientation;

	// Personnalite
	@Embedded
	private Personnalite personnalite;

	// Statut
	private boolean marie;
	private boolean adulte;
	private boolean decede;
	private boolean souhaitRealise;
	@OneToOne(cascade = CascadeType.ALL)
	private Sim conjoint;

	// starter
	private boolean starter;
	@Embedded
	private Starter infoStarter;

	// liaisons
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Famille famille;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Famille familleOrigine;

}
