package com.althaus.gemini.bootcamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.contracts.services.CategoryService;
import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;

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
	System.err.println("Aplicacion arrancada");
	List<Actor> actores = actorService.readAllList();
	System.err.println(actores);
}


}
