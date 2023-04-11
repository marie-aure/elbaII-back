package fr.liza.elba.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {

	@GetMapping("")
	public List<String> helloWorld() {
		return List.of("hello world");
	}

}
