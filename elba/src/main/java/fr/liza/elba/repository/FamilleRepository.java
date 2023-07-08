package fr.liza.elba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.liza.elba.model.jpa.Famille;

public interface FamilleRepository extends JpaRepository<Famille, Long> {

	Optional<Famille> findFirstByTourActif(boolean actif);

}
