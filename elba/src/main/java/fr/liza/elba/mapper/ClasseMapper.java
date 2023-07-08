package fr.liza.elba.mapper;

import org.mapstruct.Mapper;

import fr.liza.elba.model.dto.ClasseDto;
import fr.liza.elba.model.jpa.Classe;

@Mapper
public interface ClasseMapper {

	ClasseDto toDto(Classe classe);


}
