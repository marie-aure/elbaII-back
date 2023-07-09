package fr.liza.elba.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.liza.elba.model.dto.FinTourDto;

@RestController
@RequestMapping("/api/tour")
@CrossOrigin(origins = "*")
public class TourController {

	@PostMapping("/cloturer")
	public void cloturerTour(FinTourDto fin) {

	}

}
