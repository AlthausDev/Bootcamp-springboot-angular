package com.althaus.gemini.bootcamp.application.contracts.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.contracts.services.CategoryService;
import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;

public class CatalogoServiceImpl {
    
    @Autowired
	private ActorService actorService; 
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private LanguageService languageService;
}
