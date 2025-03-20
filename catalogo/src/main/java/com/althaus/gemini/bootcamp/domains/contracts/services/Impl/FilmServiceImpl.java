package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.FilmRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl.CoreServiceImpl;
import com.althaus.gemini.bootcamp.domains.entities.Film;

@Service
public class FilmServiceImpl extends CoreServiceImpl<Film> implements FilmService {
	
	private FilmRepository filmRepository;

	public FilmServiceImpl(FilmRepository filmRepository) {
		super(filmRepository);
		this.filmRepository = filmRepository;
	}
}
