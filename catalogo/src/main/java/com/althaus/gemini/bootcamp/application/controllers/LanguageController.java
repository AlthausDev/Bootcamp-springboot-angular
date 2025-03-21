package com.althaus.gemini.bootcamp.application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;
import com.althaus.gemini.bootcamp.domains.entities.Language;
import com.althaus.gemini.bootcamp.domains.entities.models.LanguageModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/lenguajes/v1")
public class LanguageController {
    
    @Autowired
    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los lenguajes", description = "Obtiene una lista de todos los lenguajes")
    public List<LanguageModel> getAll() {
        return languageService.readAllList().stream()
                .map(LanguageModel::from)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un lenguaje por ID", description = "Obtiene una lenguaje por su identificador")
    @ApiResponse(responseCode = "200", description = "Lenguaje encontrado")
    @ApiResponse(responseCode = "404", description = "Lenguaje no encontrado")
    public LanguageModel getById(@PathVariable int id) throws NotFoundException {
        return languageService.read(id)
                .map(LanguageModel::from)
                .orElseThrow(() -> new NotFoundException("No se encuentra el lenguaje con ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Crear un lenguaje", description = "Crea un nuevo lenguaje")
    @ApiResponse(responseCode = "201", description = "Lenguaje creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos") 
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(LanguageModel.class)
    public ResponseEntity<Object> create(@Valid @RequestBody LanguageModel item) throws BadRequestException, InvalidDataException {
        if (item.getLanguageId() != 0) {
            throw new BadRequestException("El id del lenguaje debe ser 0");
        }
        if (languageService.read(item.getLanguageId()).isPresent()) {
            throw new InvalidDataException("Duplicate key");
        }

        Language newLanguage = languageService.create(LanguageModel.from(item));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newLanguage.getLanguageId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un lenguaje", description = "Actualiza un lenguaje por su identificador")
    @ApiResponse(responseCode = "200", description = "Lenguaje actualizado")
    public void update(@PathVariable int id, @Valid @RequestBody LanguageModel item) throws NotFoundException, InvalidDataException {
        if (item.getLanguageId() != id) {
            throw new NotFoundException("El id del lenguaje no coincide");
        }

        try{
            Language language = LanguageModel.from(item);
            languageService.update(language);
        } catch (Exception e) {
            throw new InvalidDataException("Error al actualizar el lenguaje");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un lenguaje", description = "Elimina un lenguaje por su identificador")
    @ApiResponse(responseCode = "204", description = "Lenguaje eliminado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        languageService.deleteById(id);
    }
}
