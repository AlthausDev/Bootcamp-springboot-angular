package com.althaus.gemini.bootcamp.domains.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilmCategoryPKTest {

    @Test
    void testEquals_SameObject() {
        FilmCategoryPK pk1 = new FilmCategoryPK(1, 1);
        assertTrue(pk1.equals(pk1), "An object should be equal to itself.");
    }

    @Test
    void testEquals_EqualObjects() {
        FilmCategoryPK pk1 = new FilmCategoryPK(1, 1);
        FilmCategoryPK pk2 = new FilmCategoryPK(1, 1);
        assertTrue(pk1.equals(pk2), "Objects with the same values should be equal.");
    }

    @Test
    void testEquals_DifferentFilmId() {
        FilmCategoryPK pk1 = new FilmCategoryPK(1, 1);
        FilmCategoryPK pk2 = new FilmCategoryPK(2, 1);
        assertFalse(pk1.equals(pk2), "Objects with different filmId values should not be equal.");
    }

    @Test
    void testEquals_DifferentCategoryId() {
        FilmCategoryPK pk1 = new FilmCategoryPK(1, 1);
        FilmCategoryPK pk2 = new FilmCategoryPK(1, 2);
        assertFalse(pk1.equals(pk2), "Objects with different categoryId values should not be equal.");
    }

    @Test
    void testEquals_NullObject() {
        FilmCategoryPK pk1 = new FilmCategoryPK(1, 1);
        assertFalse(pk1.equals(null), "An object should not be equal to null.");
    }

    @Test
    void testEquals_DifferentClass() {
        FilmCategoryPK pk1 = new FilmCategoryPK(1, 1);
        String other = "Not a FilmCategoryPK";
        assertFalse(pk1.equals(other), "An object should not be equal to an object of a different class.");
    }
}