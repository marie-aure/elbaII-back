package fr.liza.elba.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.liza.elba.model.dto.SimDto;
import fr.liza.elba.service.StarterService;

@RestController
@RequestMapping("/api/starter")
@CrossOrigin(origins = "*")
public class StarterController {

	@Autowired
	private StarterService starterService;

	@GetMapping("/generer/{nombre}")
	public void genererStarters(@PathVariable("nombre") Integer nombre) {

		starterService.genererStarter(nombre);
	}

	@GetMapping("/liste-groupes")
	public List<Integer> listeGroupes() {
		
		return starterService.listeGroupes();
	}

	@GetMapping("/groupe/{numero}")
	public List<SimDto> chargerGroupe(@PathVariable("numero") Integer numero) {

		return starterService.chargerGroupe(numero);
	}
}
