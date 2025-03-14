package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.CoreService;

public class CoreServiceImpl<T> implements CoreService<T> {

    private final JpaRepository<T, Integer> repository;
	 private Class<T> entityClass;
	   
	    public CoreServiceImpl(CoreRepository<T, Integer> repository) {
	        this.repository = repository;
	        this.entityClass = initEntityClass();
	    }


	    private Class<T> initEntityClass() {
	        Class<?> clazz = getClass();
	        Type type = clazz.getGenericSuperclass();

	        while (!(type instanceof ParameterizedType parameterizedType)) {
	            if (clazz.getSuperclass() == null) {
	                throw new IllegalArgumentException("No se pudo determinar el tipo de la entidad.");
	            }

	            clazz = clazz.getSuperclass();
	            type = clazz.getGenericSuperclass();
	        }

	        Type[] typeArguments = parameterizedType.getActualTypeArguments();

	        if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
	            return (Class<T>) typeArguments[0];
	        } else {
	            throw new IllegalArgumentException("No se pudo determinar el tipo de la entidad.");
	        }
	    }

	 
	    @Override
	    public T create(T entity) {
	        try {
	            T savedEntity = repository.save(entity);
	            return savedEntity;
	        } catch (Exception e) {
	            throw new RuntimeException("Error al crear la entidad", e);
	        }
	    }

	    @Override
	    public Optional<T> read(Integer id) {
	        try {
	            Optional<T> entity = repository.findById(id);
	            return entity;
	        } catch (Exception e) {
	            throw new RuntimeException("Error al leer la entidad por ID", e);
	        }
	    }

	  
	    public List<T> readAllList() {
	        try {
	            return repository.findAll();
	        } catch (Exception e) {
	            throw new RuntimeException("Error al leer todas las entidades", e);
	        }
	    }

	  
	    @Override
	    public T update(T entity) {
	        try {
	            if (entity == null) {
	                throw new IllegalArgumentException("No se puede actualizar una entidad sin ID.");
	            }
	            T updatedEntity = repository.saveAndFlush(entity);
	            return updatedEntity;
	        } catch (Exception e) {
	            throw new RuntimeException("Error al actualizar la entidad", e);
	        }
	    }

	   
	    @Override
	    public void delete(T entity) {
	        try {
	            if (entity == null) {
	                throw new IllegalArgumentException("No se puede eliminar una entidad sin ID.");
	            }

	            repository.delete(entity);
	        } catch (Exception e) {
	            throw new RuntimeException("Error al eliminar la entidad", e);
	        }
	    }

	   
	    @Override
	    public void deleteById(Integer id) {
	        try {
	            if (id == null) {
	                throw new IllegalArgumentException("El ID no puede ser nulo para eliminar la entidad.");
	            }

	            repository.deleteById(id);
	        } catch (Exception e) {
	            throw new RuntimeException("Error al eliminar la entidad por ID", e);
	        }
	    }

	    
	    @Override
	    public void deleteAll(List<T> entities) {
	        try {
	            if (entities == null || entities.isEmpty()) {
	                throw new IllegalArgumentException("La lista de entidades no puede ser nula o vacía para eliminar todas las entidades.");
	            }

	            repository.deleteAll(entities);
	        } catch (Exception e) {
	            throw new RuntimeException("Error al eliminar todas las entidades", e);
	        }
	    }
}
