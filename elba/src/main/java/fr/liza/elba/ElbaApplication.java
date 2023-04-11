package fr.liza.elba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ElbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElbaApplication.class, args);
	}

}
