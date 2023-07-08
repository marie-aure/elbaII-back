package fr.liza.elba.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import fr.liza.elba.model.dto.FamilleDto;
import fr.liza.elba.model.jpa.Famille;

@Mapper(uses = { SimMapper.class, ClasseMapper.class })
public interface FamilleMapper {

	FamilleDto toDto(Famille famille);

	List<FamilleDto> toDtoList(List<Famille> familles);

}
