package fr.liza.elba.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Starter {

	@Id
	@GeneratedValue
	private Long id;

	private int peau;
	private int poids;
	private int muscle;
	private int poitrine;
	private int couleurCheveux;
	private int cheveux;
	private int visage;
	private int couleurYeux;
	private int yeux;
	private int nez;
	private int bouche;
	private int plat;
	private int musique;
	private int couleur;
	private int signe;

	private int groupe;

	@OneToOne(mappedBy = "infoStarter")
	private Sim sim;
}
