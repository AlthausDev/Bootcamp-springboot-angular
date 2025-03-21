package com.althaus.gemini.bootcamp.domains.core.contracts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;

public interface CoreService<T, E>{

	T create(T entity) throws DuplicateKeyException, InvalidDataException;

	Optional<T> read(E id);

	List<T> readAllList();

	T update(T entity) throws NotFoundException, InvalidDataException;

	void delete(T entity) throws InvalidDataException;

	void deleteById(E id);

	void deleteAll(List<T> entities);

	<U> List<U> getByProjection(Class<U> type);

	<U> Iterable<U> getByProjection(Sort sort, Class<U> type);

	<U> Page<U> getByProjection(Pageable pageable, Class<U> type);

	Iterable<T> getAll(Sort sort);

	Page<T> getAll(Pageable pageable);
}
