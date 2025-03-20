package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.ActorRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl.CoreServiceImpl;
import com.althaus.gemini.bootcamp.domains.entities.Actor;

@Service
public class ActorServiceImpl extends CoreServiceImpl<Actor, Integer> implements ActorService {
	
	
	private ActorRepository actorRepository;
	
	public ActorServiceImpl(ActorRepository actorRepository) {
		super(actorRepository);
		this.actorRepository = actorRepository;
	}
}