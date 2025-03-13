package com.althaus.gemini.bootcamp.domains.contracts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;

public interface DomainService <T, E> {
	
	Optional<T> getById(E id);
	
	List<T> getAll();
	
	T create (T item) throws DuplicateKeyException, InvalidDataException;
	T update (T item) throws NotFoundException, InvalidDataException;
	
	void delete(T item) throws InvalidDataException;
	void deteleById (E id);
	
}
