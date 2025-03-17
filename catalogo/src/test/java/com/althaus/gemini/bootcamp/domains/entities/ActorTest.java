package com.althaus.gemini.bootcamp.domains.entities;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ActorTest {

    private Actor actor;

    @BeforeEach
    public void setUp() {
        actor = new Actor();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(actor);
    }

    @Test
    public void testParameterizedConstructor() {
        Actor actor = new Actor(1, "John", "Doe");
        assertEquals(1, actor.getActorId());
        assertEquals("John", actor.getFirstName());
        assertEquals("Doe", actor.getLastName());
    }

    @Test
    public void testGettersAndSetters() {
        actor.setActorId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        actor.setLastUpdate(timestamp);

        assertEquals(1, actor.getActorId());
        assertEquals("John", actor.getFirstName());
        assertEquals("Doe", actor.getLastName());
        assertEquals(timestamp, actor.getLastUpdate());
    }

    @Test
    public void testHashCode() {
        Actor actor1 = new Actor(1, "John", "Doe");
        Actor actor2 = new Actor(1, "John", "Doe");
        assertEquals(actor1.hashCode(), actor2.hashCode());
    }

    @Test
    public void testEquals() {
        Actor actor1 = new Actor(1, "John", "Doe");
        Actor actor2 = new Actor(1, "John", "Doe");
        Actor actor3 = new Actor(2, "Jane", "Doe");

        assertTrue(actor1.equals(actor2));
        assertFalse(actor1.equals(actor3));
    }

    @Test
    public void testToString() {
        actor.setActorId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        actor.setLastUpdate(timestamp);

        String expected = "Actor [actorId=1, firstName=John, lastName=Doe, lastUpdate=" + timestamp + "]";
        assertEquals(expected, actor.toString());
    }

    @Test
    public void testAddFilmActor() {
        FilmActor filmActor = new FilmActor();
        List<FilmActor> filmActors = new ArrayList<>();
        actor.setFilmActors(filmActors);

        actor.addFilmActor(filmActor);
        assertEquals(1, actor.getFilmActors().size());
        assertEquals(actor, filmActor.getActor());
    }

    @Test
    public void testRemoveFilmActor() {
        FilmActor filmActor = new FilmActor();
        List<FilmActor> filmActors = new ArrayList<>();
        filmActors.add(filmActor);
        actor.setFilmActors(filmActors);

        actor.removeFilmActor(filmActor);
        assertEquals(0, actor.getFilmActors().size());
        assertNull(filmActor.getActor());
    }
}