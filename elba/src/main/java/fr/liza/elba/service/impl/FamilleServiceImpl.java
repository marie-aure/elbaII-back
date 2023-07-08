package fr.liza.elba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.liza.elba.mapper.FamilleMapper;
import fr.liza.elba.model.dto.FamilleDto;
import fr.liza.elba.model.jpa.Famille;
import fr.liza.elba.repository.FamilleRepository;
import fr.liza.elba.service.FamilleService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FamilleServiceImpl implements FamilleService {

	@Autowired
	private FamilleRepository familleRepository;

	@Autowired
	private FamilleMapper familleMapper;

	@Override
	public FamilleDto chargerFamilleActive() {
		Famille famille = familleRepository.findFirstByTourActif(true).orElseThrow(EntityNotFoundException::new);
		FamilleDto dto = familleMapper.toDto(famille);

		return dto;
	}


}
