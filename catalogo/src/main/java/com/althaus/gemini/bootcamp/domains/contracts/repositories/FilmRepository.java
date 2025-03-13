package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.althaus.gemini.bootcamp.domains.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{

}
