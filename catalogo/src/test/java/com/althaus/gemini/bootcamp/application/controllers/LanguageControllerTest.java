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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import com.althaus.gemini.bootcamp.domains.entities.Language;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LanguageService languageService;


    @Test
    public void testGetAll_Success() throws Exception {
        when(languageService.readAllList()).thenReturn(List.of(
                new Language(1, "English"),
                new Language(2, "Spanish")));

        mockMvc.perform(get("/api/lenguajes/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].languageId").value(1))
                .andExpect(jsonPath("$[0].name").value("English"))
                .andExpect(jsonPath("$[1].languageId").value(2))
                .andExpect(jsonPath("$[1].name").value("Spanish"));
    }

    @Test
    public void testCreate_Success() throws Exception {
        Language createdLanguage = new Language(3, "French");
        when(languageService.create(any(Language.class))).thenReturn(createdLanguage);

        mockMvc.perform(post("/api/lenguajes/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"languageId\":0,\"name\":\"French\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    public void testCreate_BadRequest() throws Exception {
        mockMvc.perform(post("/api/lenguajes/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"languageId\":1,\"name\":\"French\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdate_Success() throws Exception {
        LanguageModel updatedLanguage = new LanguageModel(1, "German");
        doNothing().when(languageService).update(any(Language.class));

        mockMvc.perform(put("/api/lenguajes/v1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"languageId\":1,\"name\":\"German\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate_BadRequest() throws Exception {
        mockMvc.perform(put("/api/lenguajes/v1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"languageId\":2,\"name\":\"German\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDelete_Success() throws Exception {
        when(languageService.read(1)).thenReturn(Optional.of(new Language(1, "English")));
        doNothing().when(languageService).deleteById(1);

        mockMvc.perform(delete("/api/lenguajes/v1/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDelete_NotFound() throws Exception {
        when(languageService.read(999)).thenThrow(new NotFoundException("No se encuentra el lenguaje con ID: 999"));

        mockMvc.perform(delete("/api/lenguajes/v1/999"))
                .andExpect(status().isNotFound());
    }
}