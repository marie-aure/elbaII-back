package fr.liza.elba.model.dto;

import java.util.List;

public class FamilleDto {

	private Long id;
	private String nom;
	private int generation;
	private int argentIG;
	private SimDto chef;
	private ClasseDto classe;
	private List<SimDto> sims;
	private List<SimDto> simsOrigine;

}
