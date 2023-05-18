package fr.liza.elba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.liza.elba.model.enumeration.TypeTrait;
import fr.liza.elba.model.jpa.Trait;

public interface TraitRepository extends JpaRepository<Trait, Long> {

	@Query("SELECT t from Trait t ORDER BY random() LIMIT 1")
	Trait findRandom();

	@Query("SELECT t from Trait t WHERE type = :type ORDER BY random() LIMIT 1")
	Trait findRandomByType(TypeTrait type);

}
