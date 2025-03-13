package com.althaus.gemini.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.ActorRepository;
import com.althaus.gemini.bootcamp.domains.contracts.repositories.CategoryRepository;
import com.althaus.gemini.bootcamp.domains.contracts.repositories.FilmRepository;
import com.althaus.gemini.bootcamp.domains.contracts.repositories.LanguageRepository;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {
	
	@Autowired
	private ActorRepository actorRepository; 
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private LanguageRepository languageRepository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		actorRepository
			.findTop10ByFirstNameStartingWithOrderByLastNameDesc("r")
			.forEach(System.err::println);;	
	}
	
	@Bean
	CommandLineRunner demo() {
		return(args)->{
			System.err.println("Aplicacion arrancada");
		};
	}

}
