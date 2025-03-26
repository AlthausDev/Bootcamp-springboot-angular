package com.althaus.gemini.bootcamp.application.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import com.althaus.gemini.bootcamp.domains.contracts.services.LanguageService;
import com.althaus.gemini.bootcamp.domains.entities.models.LanguageModel;
import com.althaus.gemini.bootcamp.utils.exceptions.NotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LanguageService languageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    // public void testGetById_Success() throws Exception {
    //     int languageId = 1;
    //     LanguageModel language = new LanguageModel(languageId, "English");
    //     when(languageService.read(languageId)).thenReturn(Optional.of(language));

    //     mockMvc.perform(get("/api/lenguajes/v1/" + languageId))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.languageId").value(languageId))
    //             .andExpect(jsonPath("$.name").value("English"));
    // }

    @Test
    public void testGetById_NotFound() throws Exception {
        int languageId = 999;
        when(languageService.read(languageId)).thenThrow(new NotFoundException("No se encuentra el lenguaje con ID: " + languageId));

        mockMvc.perform(get("/api/lenguajes/v1/" + languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetById_InternalServerError() throws Exception {
        int languageId = 1;
        when(languageService.read(languageId)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/lenguajes/v1/" + languageId))
                .andExpect(status().isInternalServerError());
    }
}