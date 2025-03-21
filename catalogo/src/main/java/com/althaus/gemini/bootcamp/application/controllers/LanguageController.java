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

import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;
import com.althaus.gemini.bootcamp.domains.entities.Language;
import com.althaus.gemini.bootcamp.domains.entities.models.LanguageModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

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
    public List<Language> getAll() {
        //return languageService.getByProjection(LanguageModel.class);
        return languageService.readAllList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un lenguaje por ID", description = "Obtiene una lenguaje por su identificador")
    @ApiResponse(responseCode = "200", description = "Lenguaje encontrado")
    @ApiResponse(responseCode = "404", description = "Lenguaje no encontrado")
    public Language getById(@PathVariable int id) throws NotFoundException {
        try{
            return languageService.read(id).get();
        } catch (Exception e) {
            throw new NotFoundException("No se encuentra el lenguaje: " + e.getMessage());
        }
    }

    //TODO revisar si se puede hacer con el modelo
    @PostMapping
    @Operation(summary = "Crear un lenguaje", description = "Crea un nuevo lenguaje")
    @ApiResponse(responseCode = "201", description = "Lenguaje creado")
    public ResponseEntity<Object> create(@Valid @RequestBody LanguageModel item) throws BadRequestException, InvalidDataException { 
        
        var newLanguage = languageService.create(LanguageModel.from(item));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(((Language)newLanguage).getLanguageId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una lenguaje", description = "Actualiza un lenguaje por su identificador")
    @ApiResponse(responseCode = "200", description = "Lenguaje actualizado")
    public void update(@PathVariable int id, @Valid @RequestBody Language item) throws NotFoundException, InvalidDataException {
        	if(item.getLanguageId() != id) {
			throw new NotFoundException("El id de la lenguaje no se encuentra");
		}
		
		try {
			languageService.update(item);
		} catch (org.springframework.data.crossstore.ChangeSetPersister.NotFoundException e) {
			throw new NotFoundException("No se encuentra el lenguaje: " + e.getMessage());
		}
	}


    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un lenguaje", description = "Elimina un lenguaje por su identificador")
    @ApiResponse(responseCode = "204", description = "Lenguaje eliminado")
    public void delete(@PathVariable int id) {
        languageService.deleteById(id);
    }
}
