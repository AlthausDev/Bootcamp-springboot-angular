package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.ActorRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl.CoreServiceImpl;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.domains.entities.models.ActorModel;

@Service
public class ActorServiceImpl extends CoreServiceImpl<Actor, Integer> implements ActorService {
	
	
	private ActorRepository actorRepository;
	
	public ActorServiceImpl(ActorRepository actorRepository) {
		super(actorRepository);
		this.actorRepository = actorRepository;
	}
}