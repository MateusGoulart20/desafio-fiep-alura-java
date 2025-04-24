package br.desafio.lambda.fiep;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.desafio.lambda.fiep.principal.Principal;

@SpringBootApplication
public class FiepApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FiepApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.iniciar();
	}

}
