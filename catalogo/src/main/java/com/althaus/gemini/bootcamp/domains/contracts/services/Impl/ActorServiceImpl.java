package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.ActorRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;

@Service
public class ActorServiceImpl extends CoreServiceImpl<Actor> implements ActorService {
	
	
	private ActorRepository actorRepository;
	
	public ActorServiceImpl(ActorRepository actorRepository) {
		super(actorRepository);
		this.actorRepository = actorRepository;
	}


//	@Override
//	public Actor create(Actor entity) {
//		try {
//			return actorRepository.save(entity);
//		} catch (Exception e) {
//			throw new RuntimeException("Error al crear la entidad", e);
//		}
//	}
//
//	@Override
//	public Optional<Actor> read(Integer id) {
//		try {
//			Optional<Actor> entity = actorRepository.findById(id);
//			return entity;
//		} catch (Exception e) {
//			throw new RuntimeException("Error al leer la entidad por ID", e);
//		}
//	}
//
//	@Override
//	public List<Actor> readAllList() {
//		actorRepository.
//		return null;
//	}
//
//	@Override
//	public Actor update(Actor entity) {
//		actorRepository.saveAndFlush(entity);
//		return null;
//	}
//
//	@Override
//	public void delete(Actor entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll(List<Actor> entities) {
//		// TODO Auto-generated method stub
//		
//	}

	
	
	
//	@Override
//	public Optional<Actor> getById(Integer id) {
//		return actorRepository.findById(id);
//	}
//
//	@Override
//	public List<Actor> getAll() {
//		return actorRepository.findAll();
//	}
//
//	@Override
//	public Actor create(Actor item) throws DuplicateKeyException, InvalidDataException {
//		if(item == null) {
//			throw new InvalidDataException("El actor no puede ser nulo");
//		} if(item.getActorId() > 0 && actorRepository.existsById(item.getActorId())){
//			throw new DuplicateKeyException("El actor ya existe");
//		}
//		return actorRepository.save(item);
//	}
//
//	@Override
//	public Actor update(Actor item) throws NotFoundException, InvalidDataException {		
//		
//		if(item == null) {
//			throw new InvalidDataException("El actor no puede ser nulo");
//		} else {
//			try {
//				return actorRepository.save(item);
//			} catch(Exception ex) {
//				throw new NotFoundException();
//			}
//		}
//		
//	}
//
//	@Override
//	public void delete(Actor item) throws InvalidDataException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deteleById(Integer id) {
//		// TODO Auto-generated method stub
//
//	}

}
