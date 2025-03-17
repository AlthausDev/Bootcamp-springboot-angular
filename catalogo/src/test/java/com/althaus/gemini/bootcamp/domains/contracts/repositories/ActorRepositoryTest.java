package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import org.springframework.data.domain.Sort;
import com.althaus.gemini.bootcamp.domains.entities.Actor;

@SpringBootTest
class ActorRepositoryTest {
	@Autowired
	ActorRepository actorRepository;
	
	@Test
	void testFindAll() {
		assertThat(actorRepository.findAll().size()).isGreaterThan(199);
	}
	
	@Test
	void testFindTop10ByFirstNameStartingWithOrderByLastNameDesc() {
		List<Actor> actors = actorRepository.findTop10ByFirstNameStartingWithOrderByLastNameDesc("A");
		assertThat(actors).isNotEmpty();
		assertThat(actors.size()).isLessThanOrEqualTo(10);
		assertThat(actors.get(0).getLastName()).isGreaterThanOrEqualTo(actors.get(actors.size() - 1).getLastName());
	}
	
	@Test
	void testFindTop10ByFirstNameStartingWith() {
		List<Actor> actors = actorRepository.findTop10ByFirstNameStartingWith("A", Sort.by("lastName").descending());
		assertThat(actors).isNotEmpty();
		assertThat(actors.size()).isLessThanOrEqualTo(10);
		assertThat(actors.get(0).getLastName()).isGreaterThanOrEqualTo(actors.get(actors.size() - 1).getLastName());
	}
	
	@Test
	void testFindByActorIdGreaterThan() {
		List<Actor> actors = actorRepository.findByActorIdGreaterThan(100);
		assertThat(actors).isNotEmpty();
		assertThat(actors.get(0).getActorId()).isGreaterThan(100);
	}
}
