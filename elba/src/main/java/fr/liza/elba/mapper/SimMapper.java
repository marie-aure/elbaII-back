package fr.liza.elba.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.liza.elba.model.dto.PersonnaliteDto;
import fr.liza.elba.model.dto.SimDto;
import fr.liza.elba.model.jpa.Personnalite;
import fr.liza.elba.model.jpa.Sim;

@Mapper
public interface SimMapper {

	@Mapping(source = "genre.libelle", target = "genre")
	@Mapping(source = "espece.libelle", target = "espece")
	@Mapping(source = "orientation.orientation", target = "orientation")
	SimDto toDto(Sim sim);

	List<SimDto> toDtoList(List<Sim> simListe);

	@Mapping(source = "souhait.libelle", target = "souhait")
	@Mapping(source = "traitMental.libelle", target = "traitMental")
	@Mapping(source = "traitPhysique.libelle", target = "traitPhysique")
	@Mapping(source = "traitSocial.libelle", target = "traitSocial")
	@Mapping(source = "traitStyleVie.libelle", target = "traitStyleVie")
	@Mapping(source = "traitBonus.libelle", target = "traitBonus")
	PersonnaliteDto toDtoPersonnalite(Personnalite personnalite);

}
