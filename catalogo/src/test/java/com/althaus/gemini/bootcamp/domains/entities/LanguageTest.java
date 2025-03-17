package com.althaus.gemini.bootcamp.domains.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class LanguageTest {

    private Language language;

    @BeforeEach
    public void setUp() {
        language = new Language();
    }

    @Test
    public void testGetAndSetLanguageId() {
        int languageId = 1;
        language.setLanguageId(languageId);
        assertEquals(languageId, language.getLanguageId());
    }

    @Test
    public void testGetAndSetLastUpdate() {
        Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
        language.setLastUpdate(lastUpdate);
        assertEquals(lastUpdate, language.getLastUpdate());
    }

    @Test
    public void testGetAndSetName() {
        String name = "English";
        language.setName(name);
        assertEquals(name, language.getName());
    }

    @Test
    public void testGetAndSetFilms() {
        List<Film> films = new ArrayList<>();
        language.setFilms(films);
        assertEquals(films, language.getFilms());
    }

    @Test
    public void testAddAndRemoveFilm() {
        Film film = new Film();
        language.setFilms(new ArrayList<>());

        language.addFilm(film);
        assertTrue(language.getFilms().contains(film));
        assertEquals(language, film.getLanguage());

        language.removeFilm(film);
        assertFalse(language.getFilms().contains(film));
        assertNull(film.getLanguage());
    }

    @Test
    public void testGetAndSetFilmsVO() {
        List<Film> filmsVO = new ArrayList<>();
        language.setFilmsVO(filmsVO);
        assertEquals(filmsVO, language.getFilmsVO());
    }

    @Test
    public void testAddAndRemoveFilmsVO() {
        Film filmVO = new Film();
        language.setFilmsVO(new ArrayList<>());

        language.addFilmsVO(filmVO);
        assertTrue(language.getFilmsVO().contains(filmVO));
        assertEquals(language, filmVO.getLanguageVO());

        language.removeFilmsVO(filmVO);
        assertFalse(language.getFilmsVO().contains(filmVO));
        assertNull(filmVO.getLanguageVO());
    }
}