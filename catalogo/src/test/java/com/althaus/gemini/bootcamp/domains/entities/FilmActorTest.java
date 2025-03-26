package com.althaus.gemini.bootcamp.domains.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;


class FilmActorTest {

    private FilmActor filmActor;
    private Film film;
    private Actor actor;

    @BeforeEach
    void setUp() {
        film = new Film();
        film.setFilmId(1);

        actor = new Actor();
        actor.setActorId(1);

        filmActor = new FilmActor(film, actor);
    }

    @Test
    void testFilmActorInitialization() {
        assertNotNull(filmActor);
        assertEquals(film, filmActor.getFilm());
        assertEquals(actor, filmActor.getActor());
    }

    @Test
    void testPrePersistSetsId() {
        filmActor.prePersiste();
        assertNotNull(filmActor.getId());
        assertEquals(film.getFilmId(), filmActor.getId().getFilmId());
        assertEquals(actor.getActorId(), filmActor.getId().getActorId());
    }

    @Test
    void testLastUpdateIsNotNull() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        filmActor.setLastUpdate(timestamp);
        assertEquals(timestamp, filmActor.getLastUpdate());
    }
}