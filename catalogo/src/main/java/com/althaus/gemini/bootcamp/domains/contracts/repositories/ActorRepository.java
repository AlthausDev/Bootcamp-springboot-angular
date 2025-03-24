package com.althaus.gemini.bootcamp.domains.contracts.repositories;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.entities.Actor;

@RepositoryRestResource(exported = false)
public interface ActorRepository extends CoreRepository<Actor, Integer> {
	
	List<Actor> findTop10ByFirstNameStartingWithOrderByLastNameDesc(String prefijo);
	List<Actor> findTop10ByFirstNameStartingWith(String prefijo, Sort orderBy);
	List<Actor> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
	List<Actor> findByActorIdGreaterThan(int actorId);
	List<Actor> findByFirstNameContaining(String firstName);
	List<Actor> findByLastNameContaining(String lastName);
	List<Actor> findByLastUpdate(Timestamp lastUpdate);
}
