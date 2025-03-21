package com.althaus.gemini.bootcamp.application.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.althaus.gemini.bootcamp.domains.contracts.services.ActorService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.domains.entities.models.ActorModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("api/actores/v1")
public class ActorController {

	@Autowired
	private ActorService actorService;


	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}
		
	// @GetMapping
	// public List<ActorModel> getAll() {
	// 	return actorService.getByProjection(ActorModel.class);
	// }

	// @GetMapping(params = { "page" })
	// @Operation(summary = "Obtiene todos los actores paginados")
	// public Page<ActorModel> getAll(@ParameterObject Pageable pageable) {
	// 	return actorService.getByProjection(pageable, ActorModel.class);
	// }

	@GetMapping
	@Operation(summary = "Obtener todos los actores", description = "Obtiene una lista de todos los actores")
	public List<Actor> getAll() {
		return actorService.readAllList();
				
	}

	@Operation(summary = "Obtener un actor por id", description = "Obtiene un actor por su id")
	@GetMapping(path = "/{id}")
	@ApiResponse(responseCode = "200", description = "Actor encontrado")
	public ActorModel getById(@PathVariable int id) throws NotFoundException {
		var item = actorService.read(id);
		
		if(item.isEmpty()) {
			throw new NotFoundException("No se encontro el actor con el id: " + id);
		}
		return ActorModel.from(item.get());
	}
	
	record Titulo(int id, String titulo) {}

	@Operation(summary = "Obtener las películas de un actor", description = "Obtiene una lista de las películas en las que ha participado un actor")
	@GetMapping(path = "/{id}/pelis")	
	@ApiResponse(responseCode = "200", description = "Películas encontradas")
	@Transactional
	public List<Titulo> getMoviesByActor(@PathVariable int id) {
		Actor actor = actorService.read(id).get();

		return actor.getFilmActors().stream()
			.map(fa -> new Titulo(fa.getFilm().getFilmId(), fa.getFilm().getTitle()))
			.toList();
	}

	@Operation(summary = "Crear un actor", description = "Crea un actor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponse(responseCode = "201", description = "Actor creado")
	public ResponseEntity<Object> create(@Valid @RequestBody ActorModel item) throws BadRequestException, InvalidDataException {
		var newItem = actorService.create(ActorModel.from(item));
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(((Actor) newItem).getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@Operation(summary = "Actualizar un actor", description = "Actualiza un actor")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiResponse(responseCode = "204", description = "Actor actualizado")
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
	
	@Operation(summary = "Eliminar un actor", description = "Elimina un actor")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiResponse(responseCode = "204", description = "Actor eliminado")
	public void delete(@PathVariable int id) {
		actorService.deleteById(id);
	}
}
