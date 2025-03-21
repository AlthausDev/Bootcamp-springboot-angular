package com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.CoreService;

public class CoreServiceImpl<T, K extends Serializable> implements CoreService<T, K> {

    private final CoreRepository<T, K> repository;
	 private Class<T> entityClass;
	   
	    public CoreServiceImpl(CoreRepository<T, K> repository) {
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
		public Optional<T> read(K id) {
			try {
				Optional<T> entity = repository.findById((K) id);
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
		public Iterable<T> getAll(Sort sort) {
			return repository.findAll(sort);

		}


		@Override
		public Page<T> getAll(Pageable pageable) {
			return repository.findAll(pageable);

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
		public void deleteById(K id) {
			try {
				if (id == null) {
					throw new IllegalArgumentException("El ID no puede ser nulo para eliminar la entidad.");
				}
		
				repository.deleteById((K) id);
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

	@Override
	public <P> List<P> getByProjection(Class<P> type) {
		return repository.findAllBy(type);
	}

	@Override
	public <P> Iterable<P> getByProjection(Sort sort, Class<P> type) {
		return repository.findAllBy(sort, type);
	}

	@Override
	public <P> Page<P> getByProjection(Pageable pageable, Class<P> type) {
		return repository.findAllBy(pageable, type);
	}

}
