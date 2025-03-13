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
import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.contracts.services.CategoryService;
import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {
	
	@Autowired
	private ActorService actorService; 
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private LanguageService languageService;

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	
	@Bean
	CommandLineRunner demo() {
		return(args)->{
			System.err.println("Aplicacion arrancada");
		};
	}

}
