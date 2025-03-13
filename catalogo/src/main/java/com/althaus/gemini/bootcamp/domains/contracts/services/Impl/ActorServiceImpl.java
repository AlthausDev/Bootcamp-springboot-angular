package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;

@Service
public class ActorServiceImpl implements ActorService {

	@Override
	public Optional<Actor> getById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Actor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor create(Actor item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor update(Actor item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deteleById(Integer id) {
		// TODO Auto-generated method stub

	}

}
