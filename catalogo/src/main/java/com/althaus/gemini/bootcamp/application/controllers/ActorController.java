package com.althaus.gemini.bootcamp.application.controllers;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.websocket.server.PathParam;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.contracts.services.CoreService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.domains.entities.models.ActorModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("api/actores/v1")
public class ActorController {

	@Autowired
	private ActorService actorService;


	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}
		
	@GetMapping
	public List<Actor> getAll() {
		return actorService.readAllList();
				
	}
	
	@GetMapping(path = "/{id}")
	public ActorModel getById(@PathVariable int id) throws NotFoundException {
		var item = actorService.read(id);
		
		if(item.isEmpty()) {
			throw new NotFoundException("No se encontro el actor con el id: " + id);
		}
		return ActorModel.from(item.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorModel item) throws BadRequestException, InvalidDataException {
		var newItem = actorService.create(ActorModel.from(item));
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(((Actor) newItem).getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, 
			@Valid @RequestBody Actor item) throws BadRequestException, NotFoundException, InvalidDataException, NotFoundException {

		if(item.getActorId() != id) {
			throw new NotFoundException("El id del actor no se encuentra");
		}
		
		try {
			actorService.update(item);
		} catch (org.springframework.data.crossstore.ChangeSetPersister.NotFoundException e) {
			throw new NotFoundException("El actor no se encuentra: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		actorService.deleteById(id);
	}
}
