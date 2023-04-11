package fr.liza.elba.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Espece {

	HUMAIN("Humain"), FEE("Fée"), VAMPIRE("Vampire"), LOUPGAROU("Loup Garou"), SORCIER("Sorcier"), SIRENE("Sirène"),
	VEGESIM("Végésim"), GENIE("Génie"), FANTOME("Fantôme"), EXTRATERRESTRE("Extraterrestre");

	private String libelle;
}
