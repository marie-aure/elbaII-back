package fr.liza.elba.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {

	MASCULIN("Masculin"), FEMININ("Féminin");

	String libelle;

}
