package com.althaus.gemini.bootcamp.application.services;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.LanguageRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl.CoreServiceImpl;
import com.althaus.gemini.bootcamp.domains.entities.Language;

@Service
public class LanguageServiceImpl extends CoreServiceImpl<Language, Integer> implements LanguageService {

	public LanguageServiceImpl(LanguageRepository languageRepository) {
		super(languageRepository);
	}
}
