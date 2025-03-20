package com.althaus.gemini.bootcamp.domains.core.contracts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;

public interface CoreService<T> {

	T create(T entity) throws DuplicateKeyException, InvalidDataException;

	Optional<T> read(Integer id);

	List<T> readAllList();

	T update(T entity) throws NotFoundException, InvalidDataException;

	void delete(T entity) throws InvalidDataException;

	void deleteById(Integer id);

	void deleteAll(List<T> entities);
}
