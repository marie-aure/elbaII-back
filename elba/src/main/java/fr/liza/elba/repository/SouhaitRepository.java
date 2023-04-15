package fr.liza.elba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.liza.elba.model.jpa.Souhait;

public interface SouhaitRepository extends JpaRepository<Souhait, Long> {

	@Query("SELECT Souhait ORDER BY random() LIMIT 1")
	Souhait findRandom();

}
