package fr.liza.elba.model.jpa;

import java.util.List;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Famille {

	@Id
	@GeneratedValue
	private Long id;

	private String nom;
	private int generation;
	private int argentIG;

	@OneToOne(cascade = CascadeType.MERGE)
	private Sim chef;

	@ManyToOne
	private Classe classe;

	@OneToMany(mappedBy = "famille", cascade = CascadeType.MERGE)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Sim> sims;

	@OneToMany(mappedBy = "familleOrigine", cascade = CascadeType.MERGE)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Sim> simsOrigine;

}
