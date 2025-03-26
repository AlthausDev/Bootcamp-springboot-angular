package com.althaus.gemini.bootcamp.application.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.althaus.gemini.bootcamp.domains.contracts.services.CategoryService;
import com.althaus.gemini.bootcamp.domains.entities.Category;
import com.althaus.gemini.bootcamp.domains.entities.models.CategoryModel;
import com.althaus.gemini.bootcamp.utils.exceptions.BadRequestException;
import com.althaus.gemini.bootcamp.utils.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @Test
    void shouldCreateCategorySuccessfully() throws Exception {
        CategoryModel categoryModel = new CategoryModel(0, null, "Action");
        Category createdCategory = new Category(1, null, "Action");

        when(categoryService.create(any(Category.class))).thenReturn(createdCategory);

        mockMvc.perform(post("/api/categorias/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\":0,\"name\":\"Action\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", URI.create("/api/categorias/v1/1").toString()));

        verify(categoryService).create(any(Category.class));
    }

    @Test
    void shouldReturnBadRequestForInvalidCategoryId() throws Exception {
        mockMvc.perform(post("/api/categorias/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\":1,\"name\":\"Action\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El id de la categoría debe ser 0"));

        verify(categoryService, never()).create(any(Category.class));
    }

    @Test
    void shouldReturnBadRequestForDuplicateCategoryId() throws Exception {
        when(categoryService.read(0)).thenReturn(Optional.of(new Category()));

        mockMvc.perform(post("/api/categorias/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\":0,\"name\":\"Adventure\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Duplicate key"));

        verify(categoryService, never()).create(any(Category.class));
    }

    @Test
    void shouldReturnInternalServerErrorOnUnexpectedException() throws Exception {
        when(categoryService.create(any(Category.class))).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(post("/api/categorias/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\":0,\"name\":\"Horror\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unexpected error"));

        verify(categoryService).create(any(Category.class));
    }
}