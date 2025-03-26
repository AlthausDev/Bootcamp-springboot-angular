package com.althaus.gemini.bootcamp.application.controllers; // Check and modify FilmModel constructor definition if missing or incorrect.

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.althaus.gemini.bootcamp.domains.contracts.services.FilmService;
import com.althaus.gemini.bootcamp.domains.entities.models.FilmModel;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmControllerTest {

    @Mock
    private FilmService filmService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll_ReturnsEmptyList_WhenNoFilmsExist() throws Exception {
        when(filmService.readAllList()).thenReturn(List.of());

        mockMvc.perform(get("/api/peliculas/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(filmService, times(1)).readAllList();
    }

    // @Test
    // void getAll_ReturnsListOfFilms_WhenFilmsExist() throws Exception {
    //     List<FilmModel> films = List.of(
    //             new FilmModel(1, "Film 1", "Description 1"), // Make sure FilmModel class has a corresponding constructor.
    //             new FilmModel(2, "Film 2", "Description 2")
    //     );
    //     when(filmService.readAllList()).thenReturn(films);

    //     mockMvc.perform(get("/api/peliculas/v1"))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.length()").value(2))
    //             .andExpect(jsonPath("$[0].filmId").value(1))
    //             .andExpect(jsonPath("$[0].title").value("Film 1"))
    //             .andExpect(jsonPath("$[1].filmId").value(2))
    //             .andExpect(jsonPath("$[1].title").value("Film 2"));

    //     verify(filmService, times(1)).readAllList();
    // }

    @Test
    void getAll_ReturnsInternalServerError_WhenExceptionThrown() throws Exception {
        when(filmService.readAllList()).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/peliculas/v1"))
                .andExpect(status().isInternalServerError());

        verify(filmService, times(1)).readAllList();
    }
}