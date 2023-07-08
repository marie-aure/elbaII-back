package fr.liza.elba.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.liza.elba.model.dto.FamilleDto;
import fr.liza.elba.service.FamilleService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/famille")
@CrossOrigin(origins = "*")
public class FamilleController {

	@Autowired
	private FamilleService familleService;

	@GetMapping("/active")
	public FamilleDto ChargerFamilleActive() {
		try {
			return familleService.chargerFamilleActive();
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Impossible de trouver la famille active");
		}
	}

}
