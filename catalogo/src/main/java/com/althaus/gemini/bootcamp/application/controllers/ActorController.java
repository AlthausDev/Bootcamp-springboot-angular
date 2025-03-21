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
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @Operation(summary = "Obtener todos los actores", description = "Obtiene una lista de todos los actores")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de actores obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Actor>> getAll() {
        try {
            List<Actor> actors = actorService.readAllList();
            return ResponseEntity.ok(actors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un actor por ID", description = "Obtiene un actor por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actor encontrado"),
        @ApiResponse(responseCode = "404", description = "Actor no encontrado")
    })
    public ResponseEntity<ActorModel> getById(@PathVariable int id) {
        try {
            ActorModel actor = actorService.read(id)
                    .map(ActorModel::from)
                    .orElseThrow(() -> new NotFoundException("No se encuentra el actor con ID: " + id));
            return ResponseEntity.ok(actor);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

	record Titulo(int id, String titulo) {}

    @GetMapping("/{id}/pelis")
    @Operation(summary = "Obtener las películas de un actor", description = "Obtiene una lista de las películas en las que ha participado un actor")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Películas encontradas"),
        @ApiResponse(responseCode = "404", description = "Actor no encontrado")
    })
    @Transactional
    public ResponseEntity<List<Titulo>> getMoviesByActor(@PathVariable int id) {
        try {
            Actor actor = actorService.read(id)
                    .orElseThrow(() -> new NotFoundException("No se encuentra el actor con ID: " + id));
            List<Titulo> movies = actor.getFilmActors().stream()
                    .map(fa -> new Titulo(fa.getFilm().getFilmId(), fa.getFilm().getTitle()))
                    .toList();
            return ResponseEntity.ok(movies);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un actor", description = "Crea un nuevo actor")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Actor creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> create(@Valid @RequestBody ActorModel item) {
        try {
            if (item.getActorId() != 0) {
                throw new BadRequestException("El id del actor debe ser 0");
            }
            if (actorService.read(item.getActorId()).isPresent()) {
                throw new InvalidDataException("Duplicate key");
            }

            Actor newActor = actorService.create(ActorModel.from(item));
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newActor.getActorId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (BadRequestException | InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un actor", description = "Actualiza un actor por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actor actualizado"),
        @ApiResponse(responseCode = "404", description = "Actor no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Actor item) throws NotFoundException {
        try {
            if (item.getActorId() != id) {
                throw new BadRequestException("El id del actor no coincide");
            }

            actorService.update(item);
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un actor", description = "Elimina un actor por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Actor eliminado"),
        @ApiResponse(responseCode = "404", description = "Actor no encontrado")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            if (!actorService.read(id).isPresent()) {
                throw new NotFoundException("No se encuentra el actor con ID: " + id);
            }
            actorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}