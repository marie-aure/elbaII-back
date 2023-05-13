package fr.liza.elba.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.liza.elba.model.enumeration.Genre;
import fr.liza.elba.model.jpa.Classe;
import fr.liza.elba.model.jpa.Famille;
import fr.liza.elba.model.jpa.Sim;
import fr.liza.elba.repository.ClasseRepository;
import fr.liza.elba.repository.FamilleRepository;
import fr.liza.elba.repository.SimRepository;
import fr.liza.elba.service.CreerFamilleService;
import fr.liza.elba.utils.ApplicationConstants;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CreerFamilleServiceImpl implements CreerFamilleService {

	@Autowired
	private SimRepository simRepository;

	@Autowired
	private ClasseRepository classeRepository;

	@Autowired
	private FamilleRepository familleRepository;

	@Override
	public List<Famille> transformerGroupe(int groupe) {

		List<Sim> starters = simRepository.findByInfoStarterGroupe(groupe);

		List<Famille> familles = new ArrayList<Famille>();

		Classe classe = classeRepository.findByLibelle(ApplicationConstants.CLASSE_PAUVRE_LIBELLE)
				.orElseThrow(EntityNotFoundException::new);

		starters.stream().forEach(sim -> {
			if (sim.getFamille() == null) {
				Sim conjoint = sim.getConjoint();
				Sim chef = genererChefFamille(sim, conjoint);
				
				Famille famille = new Famille();
				famille.setChef(chef);
				famille.setNom(chef.getNom());
				famille.setArgentIG(0);
				famille.setClasse(classe);
				
				sim.setFamille(famille);
				sim.setFamilleOrigine(famille);
				sim.setNomOrigine(sim.getNom());
				
				conjoint.setFamille(famille);
				conjoint.setFamilleOrigine(famille);
				conjoint.setNomOrigine(conjoint.getNom());
				conjoint.setNom(sim.getNom());

				famille.setSims(List.of(sim, conjoint));
				famille.setSimsOrigine(famille.getSims());

				familles.add(famille);

				// save
				familleRepository.save(famille);
				simRepository.save(sim);
				simRepository.save(conjoint);
			}
		});

		return familles;
	}

	private Sim genererChefFamille(Sim sim, Sim conjoint) {

		Sim chef;

		if (sim.getGenre() == conjoint.getGenre()) {
			Random rnd = new Random();
			List<Sim> liste = List.of(sim, conjoint);
			chef = liste.get(rnd.nextInt(1));
		} else if (sim.getGenre().equals(Genre.MASCULIN)) {
			chef = sim;
		} else {
			chef = conjoint;
		}

		return chef;
	}

}
