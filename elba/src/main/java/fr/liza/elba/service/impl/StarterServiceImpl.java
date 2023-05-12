package fr.liza.elba.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import fr.liza.elba.mapper.SimMapper;
import fr.liza.elba.model.dto.SimDto;
import fr.liza.elba.model.enumeration.Espece;
import fr.liza.elba.model.enumeration.Genre;
import fr.liza.elba.model.enumeration.Orientation;
import fr.liza.elba.model.enumeration.TypeTrait;
import fr.liza.elba.model.jpa.Personnalite;
import fr.liza.elba.model.jpa.Sim;
import fr.liza.elba.model.jpa.Starter;
import fr.liza.elba.model.jpa.Trait;
import fr.liza.elba.repository.SimRepository;
import fr.liza.elba.repository.SouhaitRepository;
import fr.liza.elba.repository.TraitRepository;
import fr.liza.elba.service.StarterService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StarterServiceImpl implements StarterService {

	@Autowired
	private SouhaitRepository souhaitRepository;

	@Autowired
	private TraitRepository traitRepository;

	@Autowired
	private SimRepository simRepository;

	@Autowired
	private SimMapper simMapper;

	@Value("${starter.score.min}")
	private int scoreMin;

	@Override
	public void genererStarter(int nombre) {
		List<Sim> simList = creerStarter(nombre);

		Map<Pair<Sim, Sim>, Integer> mapScores = new HashMap<Pair<Sim, Sim>, Integer>();
		mapScores.putAll(calculerScores(simList, Genre.FEMININ, Genre.MASCULIN, Orientation.E));
		mapScores.putAll(calculerScores(simList, Genre.FEMININ, Genre.FEMININ, Orientation.O));
		mapScores.putAll(calculerScores(simList, Genre.MASCULIN, Genre.MASCULIN, Orientation.O));

		List<Pair<Sim, Sim>> coupleList = formerCouples(mapScores);

		formerGroupes(coupleList);

	}

	private List<Sim> creerStarter(int nombre) {
		// creer sims
		List<Sim> simList = new ArrayList<Sim>();

		// TODO souhait et traits
		int[] enumEspece = { 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] enumOrientation = { 1, 0, 0, 0 };

		Random rnd = new Random();

		for (int i = 0; i < nombre; i++) {
			Sim sim = new Sim();
			sim.setPrenom(String.valueOf((char) (rnd.nextInt(26) + 'A')));
			sim.setNom(String.valueOf((char) (rnd.nextInt(26) + 'A')));
			sim.setGenre(Genre.values()[rnd.nextInt(2)]);
			sim.setEspece(Espece.values()[enumEspece[rnd.nextInt(15)]]);
			sim.setOrientation(Orientation.values()[enumOrientation[rnd.nextInt(4)]]);
			sim.setMarie(false);
			sim.setSouhaitRealise(false);
			sim.setDecede(false);
			sim.setAdulte(true);
			sim.setStarter(true);

			sim.setPersonnalite(creerSimPersonnalite());
			sim.setInfoStarter(creerStarter(rnd));
			simList.add(sim);
		}
		return simList;
	}

	private Personnalite creerSimPersonnalite() {
		Personnalite personnalite = new Personnalite();

		personnalite.setSouhait(souhaitRepository.findRandom());
		personnalite.setTraitMental(traitRepository.findRandomByType(TypeTrait.MENTAL));
		personnalite.setTraitPhysique(traitRepository.findRandomByType(TypeTrait.PHYSIQUE));
		personnalite.setTraitSocial(traitRepository.findRandomByType(TypeTrait.SOCIAL));
		personnalite.setTraitStyleVie(traitRepository.findRandomByType(TypeTrait.STYLE_VIE));
		List<Trait> traits = List.of(personnalite.getTraitMental(), personnalite.getTraitPhysique(),
				personnalite.getTraitSocial(), personnalite.getTraitStyleVie());
		while (personnalite.getTraitBonus() == null || traits.contains(personnalite.getTraitBonus())) {
			personnalite.setTraitBonus(traitRepository.findRandom());
		}
		return personnalite;
	}

	private Starter creerStarter(Random rnd) {
		Starter starter = new Starter();

		starter.setPeau(rnd.nextInt(10) + 1);
		starter.setPoids(rnd.nextInt(10) + 1);
		starter.setMuscle(rnd.nextInt(10) + 1);
		starter.setPoitrine(rnd.nextInt(10) + 1);
		starter.setCouleurCheveux(rnd.nextInt(8) + 1);
		starter.setCheveux(rnd.nextInt(8) + 1);
		starter.setVisage(rnd.nextInt(12) + 1);
		starter.setCouleurYeux(rnd.nextInt(8) + 1);
		starter.setYeux(rnd.nextInt(15) + 1);
		starter.setNez(rnd.nextInt(24) + 1);
		starter.setBouche(rnd.nextInt(17) + 1);
		starter.setPlat(rnd.nextInt(51) + 1);
		starter.setMusique(rnd.nextInt(28) + 1);
		starter.setCouleur(rnd.nextInt(20) + 1);
		starter.setSigne(rnd.nextInt(12) + 1);

		return starter;
	}

	private Map<Pair<Sim, Sim>, Integer> calculerScores(List<Sim> simList, Genre genre1, Genre genre2,
			Orientation orientation) {

		Map<Pair<Sim, Sim>, Integer> mapScores = new HashMap<>();

		List<Sim> partenaire1List = simList.stream()
				.filter(sim -> sim.getGenre() == genre1 && sim.getOrientation() == orientation).toList();
		List<Sim> partenaire2List = simList.stream()
				.filter(sim -> sim.getGenre() == genre2 && sim.getOrientation() == orientation).toList();

		partenaire1List.stream().forEach(partenaire1 -> {
			partenaire2List.stream().forEach(partenaire2 -> {
				if (!partenaire1.equals(partenaire2)) {
					int scoreCouple = calculerScoreCouple(partenaire1, partenaire2);
					mapScores.put(Pair.of(partenaire1, partenaire2), Integer.valueOf(scoreCouple));
				}
			});
		});

		return mapScores;
	}

	private int calculerScoreCouple(Sim partenaire1, Sim partenaire2) {
		int score = 0;

		// espece surnaturel + humain ++ surnat pareil +++
		if (partenaire1.getEspece() == partenaire2.getEspece() && partenaire1.getEspece() != Espece.HUMAIN) {
			score += 20;
		} else if (partenaire1.getEspece() == Espece.HUMAIN && partenaire2.getEspece() == Espece.HUMAIN) {
			score += 15;
		} else if (partenaire1.getEspece() != Espece.HUMAIN && partenaire2.getEspece() != Espece.HUMAIN) {
			score += 10;
		}

		// souhait
		if (partenaire1.getPersonnalite().getSouhait() == partenaire2.getPersonnalite().getSouhait()) {
			score += 20;
		}

		// traits
		List<Trait> traitsPartenaire1 = List.of(partenaire1.getPersonnalite().getTraitMental(),
				partenaire1.getPersonnalite().getTraitPhysique(), partenaire1.getPersonnalite().getTraitSocial(),
				partenaire1.getPersonnalite().getTraitStyleVie(), partenaire1.getPersonnalite().getTraitBonus());

		List<Trait> traitsPartenaire2 = List.of(partenaire2.getPersonnalite().getTraitMental(),
				partenaire2.getPersonnalite().getTraitPhysique(), partenaire2.getPersonnalite().getTraitSocial(),
				partenaire2.getPersonnalite().getTraitStyleVie(), partenaire2.getPersonnalite().getTraitBonus());

		boolean correspondanceTotale = true;
		for (Trait trait : traitsPartenaire1) {
			if (traitsPartenaire2.contains(trait)) {
				score += 5;
			} else {
				correspondanceTotale = false;
			}
		}

		if (correspondanceTotale) {
			score += 5;
		}

		// peau
		score = score + 10 - Math.abs(partenaire1.getInfoStarter().getPeau() - partenaire2.getInfoStarter().getPeau());

		correspondanceTotale = true;
		// plat
		if (partenaire1.getInfoStarter().getPlat() == partenaire2.getInfoStarter().getPlat()) {
			score = score + 5;
		} else {
			correspondanceTotale = false;
		}
		// musique
		if (partenaire1.getInfoStarter().getMusique() == partenaire2.getInfoStarter().getMusique()) {
			score = score + 5;
		} else {
			correspondanceTotale = false;
		}
		// couleur
		if (partenaire1.getInfoStarter().getCouleur() == partenaire2.getInfoStarter().getCouleur()) {
			score = score + 5;
		} else {
			correspondanceTotale = false;
		}

		if (correspondanceTotale) {
			score = score + 5;
		}

		// signe
		if (partenaire1.getInfoStarter().getSigne() == partenaire2.getInfoStarter().getSigne()) {
			score = score + 10;
		}

		return score;
	}

	private List<Pair<Sim, Sim>> formerCouples(Map<Pair<Sim, Sim>, Integer> mapScores) {
		List<Sim> simCoupleList = new ArrayList<Sim>();
		List<Pair<Sim, Sim>> coupleList = new ArrayList<Pair<Sim, Sim>>();

		mapScores.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.filter(entry -> entry.getValue() >= scoreMin).forEach(entry -> {
					if (!simCoupleList.contains(entry.getKey().getFirst())
							&& !simCoupleList.contains(entry.getKey().getSecond())) {
						coupleList.add(entry.getKey());
						simCoupleList.add(entry.getKey().getFirst());
						simCoupleList.add(entry.getKey().getSecond());
					}
				});

		Collections.shuffle(coupleList);

		return coupleList;

	}

	private void formerGroupes(List<Pair<Sim, Sim>> coupleList) {

		Integer groupeId = simRepository.findMaxGroupe().orElse(-1);
		int qteGroupe = (int) Math.floor(coupleList.size() / 8);

		for (int gr = 0; gr < qteGroupe; gr++) {
			groupeId++;
			for (int co = 0; co < 8; co++) {
				Sim sim1 = coupleList.get(gr * 8 + co).getFirst();
				Sim sim2 = coupleList.get(gr * 8 + co).getSecond();
				sim1.getInfoStarter().setGroupe(groupeId);
				sim1.setConjoint(sim2);
				sim2.getInfoStarter().setGroupe(groupeId);
				sim2.setConjoint(sim1);

				simRepository.save(sim1);
				simRepository.save(sim2);
			}
		}

	}

	@Override
	public List<Integer> listeGroupes() {
		return simRepository.findDistinctGroupe();
	}

	@Override
	public List<SimDto> chargerGroupe(Integer numero) {
		List<Sim> simListe = simRepository.findByInfoStarterGroupe(numero);

		return simMapper.toDtoList(simListe);
	}

	@Override
	public SimDto completerStarter(SimDto simDto) throws EntityNotFoundException {
		Sim sim = simRepository.findById(simDto.getId()).orElseThrow(EntityNotFoundException::new);
		
		sim.setPrenom(simDto.getPrenom());
		sim.setNom(simDto.getNom());
		
		sim = simRepository.save(sim);
		
		return simMapper.toDto(sim);
	}
}
