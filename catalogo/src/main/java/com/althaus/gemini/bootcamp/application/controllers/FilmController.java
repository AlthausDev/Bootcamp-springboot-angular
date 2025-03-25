package com.althaus.gemini.bootcamp.application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.entities.Film;
import com.althaus.gemini.bootcamp.domains.entities.models.FilmModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/peliculas/v1")
public class FilmController {
    
    @Autowired
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las películas", description = "Obtiene una lista de todas las películas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de películas obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<FilmModel>> getAll() {
        try {
            List<FilmModel> films = filmService.readAllList().stream()
                    .map(FilmModel::from)
                    .toList();
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una película por ID", description = "Obtiene una película por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Película encontrada"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    public ResponseEntity<FilmModel> getById(@PathVariable int id) {
        try {
            FilmModel film = filmService.read(id)
                    .map(FilmModel::from)
                    .orElseThrow(() -> new NotFoundException("Película no encontrada con ID: " + id));
            return ResponseEntity.ok(film);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @Operation(summary = "Crear una película", description = "Crea una nueva película")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Película creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Object> create(@Valid @RequestBody FilmModel item) {
        try {
            if (item.getFilmId() != 0) {
                throw new BadRequestException("El id de la película debe ser 0");
            }
            Film newItem = filmService.create(FilmModel.from(item));
		    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (BadRequestException | InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una película", description = "Actualiza una película por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Película actualizada"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody FilmModel item) {
        try {
            if (item.getFilmId() != id) {
                throw new BadRequestException("El id de la película no coincide");
            }

            FilmModel newItem = FilmModel.from(filmService.update(FilmModel.from(item)));
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
            return ResponseEntity.created(location).build();

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una película", description = "Elimina una película por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Película eliminada"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            if (!filmService.read(id).isPresent()) {
                throw new NotFoundException("Película no encontrada con ID: " + id);
            }
            filmService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}