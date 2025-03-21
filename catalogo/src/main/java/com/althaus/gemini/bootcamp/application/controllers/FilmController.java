package com.althaus.gemini.bootcamp.application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.domains.entities.Film;
import com.althaus.gemini.bootcamp.domains.entities.Film;
import com.althaus.gemini.bootcamp.domains.entities.models.FilmModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Obtener todas las peliculas", description = "Obtiene una lista de todas las peliculas")
    public List<Film> getAll() {
        //return filmService.getByProjection(FilmModel.class);
        return filmService.readAllList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una pelicula por ID", description = "Obtiene una pelicula por su identificador")

    public Film getById(@PathVariable int id) {
        return filmService.read(id).orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
    }

    //TODO revisar si se puede hacer con el modelo
    @PostMapping
    @Operation(summary = "Crear una pelicula", description = "Crea una nueva pelicula")
    @ApiResponse(responseCode = "201", description = "Pelicula creada")
    public ResponseEntity<Object> create(@Valid @RequestBody FilmModel item) throws BadRequestException, InvalidDataException { 
        
        var newFilm = filmService.create(FilmModel.from(item));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(((Film)newFilm).getFilmId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una pelicula", description = "Actualiza una pelicula por su identificador")
    @ApiResponse(responseCode = "200", description = "Pelicula actualizada")
    public void update(@PathVariable int id, @Valid @RequestBody Film item) throws NotFoundException, InvalidDataException {
        	if(item.getFilmId() != id) {
			throw new NotFoundException("El id de la pelicula no se encuentra");
		}
		
		try {
			filmService.update(item);
		} catch (org.springframework.data.crossstore.ChangeSetPersister.NotFoundException e) {
			throw new NotFoundException("La Pelicula no se encuentra: " + e.getMessage());
		}
	}


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una pelicula", description = "Elimina una pelicula por su identificador")
    @ApiResponse(responseCode = "204", description = "Pelicula eliminada")
    public void delete(@PathVariable int id) {
        filmService.deleteById(id);
    }
}
