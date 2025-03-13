package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.LanguageRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;
import com.althaus.gemini.bootcamp.domains.entities.Language;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;

@Service
public class LanguageServiceImpl implements LanguageService {

	private LanguageRepository languageRepository;
	
	@Override
	public Optional<Language> getById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Language> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language create(Language item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language update(Language item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deteleById(Integer id) {
		// TODO Auto-generated method stub

	}

}
