package fr.liza.elba.model.jpa;

import java.util.List;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Classe {

	@Id
	@GeneratedValue
	private Long id;

	private String libelle;

	@OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Famille> familles;

	@OneToOne(cascade = CascadeType.DETACH)
	private Classe classeSup;

	@OneToOne(mappedBy = "classeSup", cascade = CascadeType.DETACH)
	private Classe classeInf;

}
