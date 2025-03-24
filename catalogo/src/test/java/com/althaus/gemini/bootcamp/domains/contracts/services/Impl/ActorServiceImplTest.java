package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.althaus.gemini.bootcamp.application.services.ActorServiceImpl;
import com.althaus.gemini.bootcamp.domains.contracts.repositories.ActorRepository;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import java.util.Optional;

public class ActorServiceImplTest {

    private ActorServiceImpl actorServiceImpl;
    private ActorRepository actorRepository;

        @BeforeEach
        public void setUp() {
            actorRepository = mock(ActorRepository.class);
            actorServiceImpl = new ActorServiceImpl(actorRepository);
        }

        @Test
        public void testActorServiceImplCreation() {
            assertNotNull(actorServiceImpl);
        }

        @Test
        public void testFindById() {
            Integer actorId = 1;
            Actor actor = new Actor();
            when(actorRepository.findById(actorId)).thenReturn(Optional.of(actor));

            Optional<Actor> result = actorServiceImpl.read(actorId);

            assertNotNull(result);
            verify(actorRepository, times(1)).findById(actorId);
        }

        @Test
        public void testSaveActor() {
            Actor actor = new Actor();
            when(actorRepository.save(actor)).thenReturn(actor);

            Actor result = actorServiceImpl.create(actor);

            assertNotNull(result);
            verify(actorRepository, times(1)).save(actor);
        }
    }
