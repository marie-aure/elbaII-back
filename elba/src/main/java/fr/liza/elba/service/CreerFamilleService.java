package fr.liza.elba.service;

import java.util.List;

import fr.liza.elba.model.jpa.Famille;

public interface CreerFamilleService {

	List<Famille> transformerGroupe(int groupe);

}
