package fr.liza.elba.model.jpa;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Starter {

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

}
