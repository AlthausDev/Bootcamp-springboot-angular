package com.althaus.gemini.bootcamp.application.controllers;

import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.domains.entities.Film;
import com.althaus.gemini.bootcamp.domains.entities.FilmActor;
import com.althaus.gemini.bootcamp.domains.entities.models.ActorModel;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ActorControllerTest {

    private ActorController actorController; // Add actorController field

    @Mock
    private ActorService actorService; // Add actorService mock

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        actorController = new ActorController(actorService); // Initialize actorController with the mock
    }

    @Test
    void getAll() {
        // Arrange
        List<Actor> mockActors = List.of(
                new Actor(1, "Actor 1", "Bio 1"),
                new Actor(2, "Actor 2", "Bio 2")
        );
        when(actorService.readAllList()).thenReturn(mockActors);

        // Act
        ResponseEntity<List<ActorModel>> response = actorController.getAll();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Actor 1", response.getBody().get(0).getFirstName());
    }

    @Test
    void getById() {
        // Arrange
        int actorId = 1;
        Actor mockActor = new Actor(actorId, "Actor 1", "Bio 1");
        when(actorService.read(actorId)).thenReturn(java.util.Optional.of(mockActor));

        // Act
        ResponseEntity<ActorModel> response = actorController.getById(actorId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Actor 1", response.getBody().getFirstName());
    }

    @Test
    void getByIdNotFound() {
        // Arrange
        int actorId = 1;
        when(actorService.read(actorId)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<ActorModel> response = actorController.getById(actorId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getByIdInternalServerError() {
        // Arrange
        int actorId = 1;
        when(actorService.read(actorId)).thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<ActorModel> response = actorController.getById(actorId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void createInvalid() {
        // Arrange
        ActorModel actorModel = new ActorModel(1, "John", "Doe");

        // Act
        ResponseEntity<Object> response = actorController.create(actorModel);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void update() throws InvalidDataException, ChangeSetPersister.NotFoundException {
        // Arrange
        int actorId = 1;
        ActorModel actorModel = new ActorModel(actorId, "John", "Doe");
        Actor updatedActor = new Actor(actorId, "John", "Doe");

        when(actorService.update(ActorModel.from(actorModel))).thenReturn(updatedActor);

        // Act
        ResponseEntity<Object> response = actorController.update(actorId, actorModel);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateInvalidId() {
        // Arrange
        ActorModel actorModel = new ActorModel(2, "John", "Doe");

        // Act
        ResponseEntity<Object> response = actorController.update(1, actorModel);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void delete() {
        // Arrange
        int actorId = 1;
        when(actorService.read(actorId)).thenReturn(java.util.Optional.of(new Actor(actorId, "John", "Doe")));

        // Act
        ResponseEntity<Object> response = actorController.delete(actorId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteNotFound() {
        // Arrange
        int actorId = 1;
        when(actorService.read(actorId)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<Object> response = actorController.delete(actorId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}