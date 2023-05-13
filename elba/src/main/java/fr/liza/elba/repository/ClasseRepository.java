package fr.liza.elba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.liza.elba.model.jpa.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

	Optional<Classe> findByLibelle(String libelle);

}
