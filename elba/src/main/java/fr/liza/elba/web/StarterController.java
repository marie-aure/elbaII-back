package fr.liza.elba.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/starter")
public class StarterController {

	@GetMapping("")
	public List<String> helloWorld() {
		return List.of("hello world");
	}

}
