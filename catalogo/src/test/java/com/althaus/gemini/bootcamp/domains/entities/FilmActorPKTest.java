

package com.althaus.gemini.bootcamp.domains.entities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class FilmActorPKTest {

    @Test
    void testEquals_SameObject() {
        FilmActorPK pk1 = new FilmActorPK(1, 1);
        assertTrue(pk1.equals(pk1), "An object should be equal to itself.");
    }

    @Test
    void testEquals_EqualObjects() {
        FilmActorPK pk1 = new FilmActorPK(1, 1);
        FilmActorPK pk2 = new FilmActorPK(1, 1);
        assertTrue(pk1.equals(pk2), "Objects with the same actorId and filmId should be equal.");
    }

    @Test
    void testEquals_DifferentActorId() {
        FilmActorPK pk1 = new FilmActorPK(1, 1);
        FilmActorPK pk2 = new FilmActorPK(2, 1);
        assertFalse(pk1.equals(pk2), "Objects with different actorId should not be equal.");
    }

    @Test
    void testEquals_DifferentFilmId() {
        FilmActorPK pk1 = new FilmActorPK(1, 1);
        FilmActorPK pk2 = new FilmActorPK(1, 2);
        assertFalse(pk1.equals(pk2), "Objects with different filmId should not be equal.");
    }

    @Test
    void testEquals_NullObject() {
        FilmActorPK pk1 = new FilmActorPK(1, 1);
        assertFalse(pk1.equals(null), "An object should not be equal to null.");
    }

    @Test
    void testEquals_DifferentClass() {
        FilmActorPK pk1 = new FilmActorPK(1, 1);
        String other = "Not a FilmActorPK";
        assertFalse(pk1.equals(other), "An object should not be equal to an object of a different class.");
    }
}