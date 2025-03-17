package com.althaus.gemini.bootcamp.domains.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class FilmTest {

    private Film film;

    @BeforeEach
    public void setUp() {
        film = new Film();
    }

    @Test
    public void testGetAndSetFilmId() {
        film.setFilmId(1);
        assertEquals(1, film.getFilmId());
    }

    @Test
    public void testGetAndSetDescription() {
        String description = "A great film";
        film.setDescription(description);
        assertEquals(description, film.getDescription());
    }

    @Test
    public void testGetAndSetLastUpdate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        film.setLastUpdate(timestamp);
        assertEquals(timestamp, film.getLastUpdate());
    }

    @Test
    public void testGetAndSetLength() {
        film.setLength(120);
        assertEquals(120, film.getLength());
    }

    @Test
    public void testGetAndSetRating() {
        String rating = "A";
        film.setRating(rating);
        assertEquals(rating, film.getRating());
    }

    @Test
    public void testGetAndSetReleaseYear() {
        Short releaseYear = 2021;
        film.setReleaseYear(releaseYear);
        assertEquals(releaseYear, film.getReleaseYear());
    }

    @Test
    public void testGetAndSetRentalDuration() {
        byte rentalDuration = 5;
        film.setRentalDuration(rentalDuration);
        assertEquals(rentalDuration, film.getRentalDuration());
    }

    @Test
    public void testGetAndSetRentalRate() {
        BigDecimal rentalRate = new BigDecimal("4.99");
        film.setRentalRate(rentalRate);
        assertEquals(rentalRate, film.getRentalRate());
    }

    @Test
    public void testGetAndSetReplacementCost() {
        BigDecimal replacementCost = new BigDecimal("19.99");
        film.setReplacementCost(replacementCost);
        assertEquals(replacementCost, film.getReplacementCost());
    }

    @Test
    public void testGetAndSetTitle() {
        String title = "Film Title";
        film.setTitle(title);
        assertEquals(title, film.getTitle());
    }

    @Test
    public void testGetAndSetLanguage() {
        Language language = new Language();
        film.setLanguage(language);
        assertEquals(language, film.getLanguage());
    }

    @Test
    public void testGetAndSetLanguageVO() {
        Language languageVO = new Language();
        film.setLanguageVO(languageVO);
        assertEquals(languageVO, film.getLanguageVO());
    }

    @Test
    public void testGetAndSetFilmActors() {
        FilmActor filmActor = new FilmActor();
        film.setFilmActors(new ArrayList<>());
        film.addFilmActor(filmActor);
        assertTrue(film.getFilmActors().contains(filmActor));
    }

    @Test
    public void testAddAndRemoveFilmActor() {
        FilmActor filmActor = new FilmActor();
        film.setFilmActors(new ArrayList<>());
        film.addFilmActor(filmActor);
        assertTrue(film.getFilmActors().contains(filmActor));
        film.removeFilmActor(filmActor);
        assertFalse(film.getFilmActors().contains(filmActor));
    }

    @Test
    public void testGetAndSetFilmCategories() {
        FilmCategory filmCategory = new FilmCategory();
        film.setFilmCategories(new ArrayList<>());
        film.addFilmCategory(filmCategory);
        assertTrue(film.getFilmCategories().contains(filmCategory));
    }

    @Test
    public void testAddAndRemoveFilmCategory() {
        FilmCategory filmCategory = new FilmCategory();
        film.setFilmCategories(new ArrayList<>());
        film.addFilmCategory(filmCategory);
        assertTrue(film.getFilmCategories().contains(filmCategory));
        film.removeFilmCategory(filmCategory);
        assertFalse(film.getFilmCategories().contains(filmCategory));
    }
}