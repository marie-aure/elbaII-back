package fr.liza.elba.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimDto {

	private Long id;
	private String prenom;
	private String nom;
	private String genre;
	private String espece;
	private String orientation;

	private PersonnaliteDto personnalite;

	private boolean marie;
	private boolean adulte;
	private boolean decede;
	private boolean souhaitRealise;

	private ConjointDto conjoint;

	private boolean starter;
	private StarterDto infoStarter;

}
