package com.althaus.gemini.bootcamp.application.services;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.FilmRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl.CoreServiceImpl;
import com.althaus.gemini.bootcamp.domains.entities.Film;

@Service
public class FilmServiceImpl extends CoreServiceImpl<Film, Integer> implements FilmService {
	
	public FilmServiceImpl(FilmRepository filmRepository) {
		super(filmRepository);
	}
}
