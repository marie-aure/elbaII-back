package fr.liza.elba.service;

import java.util.List;

import fr.liza.elba.model.dto.SimDto;

public interface StarterService {

	void genererStarter(int nombre);

	List<Integer> listeGroupes();

	List<SimDto> chargerGroupe(Integer numero);

	SimDto completerStarter(SimDto simDto);
}
