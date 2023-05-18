package fr.liza.elba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.liza.elba.model.jpa.Sim;

public interface SimRepository extends JpaRepository<Sim, Long> {

	@Query("SELECT max(s.infoStarter.groupe) FROM Sim s")
	Optional<Integer> findMaxGroupe();

	@Query("SELECT distinct s.infoStarter.groupe FROM Sim s ORDER BY s.infoStarter.groupe")
	List<Integer> findDistinctGroupe();

	List<Sim> findByInfoStarterGroupe(int groupe);

}
