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

    // @GetMapping
    // @Operation(summary = "Obtener todas las categorías", description = "Obtiene una lista de todas las categorías")
    // public List<CategoryModel> getAll() {
    //     return categoryService.getByProjection(CategoryModel.class);
    // }

    // @GetMapping("/{id}")
    // @Operation(summary = "Obtener una categoría por ID", description = "Obtiene una categoría por su identificador")

    // public Category getById(@PathVariable int id) {
    //     return categoryService.getById(id, CategoryModel.class);
    // }

    // @PostMapping
    // @Operation(summary = "Crear una categoría", description = "Crea una nueva categoría")
    // @ApiResponse(responseCode = "201", description = "Categoría creada")
    // public ResponseEntity<Category> create(@Valid @RequestBody CategoryModel categoryModel) {
        
    //     Category category = categoryService.create(categoryModel.toEntity());
    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getCategoryId()).toUri();
    //     return ResponseEntity.created(location).body(category);
    // }


    // @PutMapping("/{id}")
    // @Operation(summary = "Actualizar una categoría", description = "Actualiza una categoría por su identificador")
    // @ApiResponse(responseCode = "200", description = "Categoría actualizada")
    // public Category update(@PathVariable int id, @Valid @RequestBody CategoryModel categoryModel) {
    //     return categoryService.update(id, categoryModel, CategoryModel.class);
    // }


    // @DeleteMapping("/{id}")
    // @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría por su identificador")
    // @ApiResponse(responseCode = "204", description = "Categoría eliminada")
    // public void delete(@PathVariable int id) {
    //     categoryService.delete(id);
    // }


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
