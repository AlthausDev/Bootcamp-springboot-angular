package com.althaus.gemini.bootcamp.domains.contracts.repositories;


import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.althaus.gemini.bootcamp.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	
	List<Actor>findTop10ByFirstNameStartingWithOrderByLastNameDesc(String prefijo);
	List<Actor>findTop10ByFirstNameStartingWith(String prefijo, Sort orderBy);
	
	List<Actor> findByActorIdGreaterThan(int actorId);
	
	@Query(value = "SELECT a FROM Actor a WHERE a.actorId > ?1")
	List<Actor> findNovedadesJPQL(int actorId);


}
