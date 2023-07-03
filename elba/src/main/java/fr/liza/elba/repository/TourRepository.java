package fr.liza.elba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.liza.elba.model.jpa.Classe;
import fr.liza.elba.model.jpa.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {

	Optional<Tour> findFirstByFamilleClasseOrderByNumero(Classe classe);

}
