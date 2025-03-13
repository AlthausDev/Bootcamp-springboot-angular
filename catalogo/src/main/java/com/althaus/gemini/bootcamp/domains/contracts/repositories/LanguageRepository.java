package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.althaus.gemini.bootcamp.domains.entities.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>{

}
