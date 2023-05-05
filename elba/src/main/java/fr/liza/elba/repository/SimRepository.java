package fr.liza.elba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.liza.elba.model.jpa.Sim;

public interface SimRepository extends JpaRepository<Sim, Long> {

	@Query("Select max(s.infoStarter.groupe) from Sim s")
	Optional<Integer> findMaxGroup();

}
