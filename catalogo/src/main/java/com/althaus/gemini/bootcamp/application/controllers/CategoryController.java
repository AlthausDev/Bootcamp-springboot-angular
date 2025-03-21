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

import com.althaus.gemini.bootcamp.domains.contracts.services.CategoryService;
import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.althaus.gemini.bootcamp.domains.entities.Category;
import com.althaus.gemini.bootcamp.domains.entities.Film;
import com.althaus.gemini.bootcamp.domains.entities.models.CategoryModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categorias/v1")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorías", description = "Obtiene una lista de todas las categorías")
    public List<Category> getAll() {
        //return categoryService.getByProjection(CategoryModel.class);
        return categoryService.readAllList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por ID", description = "Obtiene una categoría por su identificador")

    public Category getById(@PathVariable int id) {
        return categoryService.read(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    //TODO revisar si se puede hacer con el modelo
    @PostMapping
    @Operation(summary = "Crear una categoría", description = "Crea una nueva categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada")
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryModel item) throws BadRequestException, InvalidDataException { 
        
        var newCategory = categoryService.create(CategoryModel.from(item));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(((Category)newCategory).getCategoryId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría", description = "Actualiza una categoría por su identificador")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada")
    public void update(@PathVariable int id, @Valid @RequestBody Category item) throws NotFoundException, InvalidDataException {
        	if(item.getCategoryId() != id) {
			throw new NotFoundException("El id de la categoría no se encuentra");
		}
		
		try {
			categoryService.update(item);
		} catch (org.springframework.data.crossstore.ChangeSetPersister.NotFoundException e) {
			throw new NotFoundException("La Categoria no se encuentra: " + e.getMessage());
		}
	}


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría por su identificador")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada")
    public void delete(@PathVariable int id) {
        categoryService.deleteById(id);
    }


    // @GetMapping("/{id}/peliculas")
    // @Operation(summary = "Obtener películas por categoría", description = "Obtiene una lista de películas por categoría")
    // public List<Film> getMoviesByCategory(@PathVariable int id) {
    //     return categoryService.getMoviesByCategory(id);
    // }

    // @GetMapping("/{id}/actores")
    // @Operation(summary = "Obtener actores por categoría", description = "Obtiene una lista de actores por categoría")
    // public List<Actor> getActorsByCategory(@PathVariable int id) {
    //     return categoryService.getActorsByCategory(id);
    // }
}
