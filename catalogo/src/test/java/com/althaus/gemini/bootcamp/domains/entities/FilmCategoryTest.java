
package com.althaus.gemini.bootcamp.domains.entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmCategoryTest {

    private FilmCategory filmCategory;
    private Film mockFilm;
    private Category mockCategory;

    @BeforeEach
    void setUp() {
        mockFilm = mock(Film.class);
        mockCategory = mock(Category.class);

        when(mockFilm.getFilmId()).thenReturn(1);
        when(mockCategory.getCategoryId()).thenReturn(2);

        filmCategory = new FilmCategory(mockFilm, mockCategory);
    }

    @Test
    void testConstructor() {
        assertEquals(mockFilm, filmCategory.getFilm());
        assertEquals(mockCategory, filmCategory.getCategory());
    }

    @Test
    void testPrePersistSetsId() {
        filmCategory.setId(null); // Ensure ID is null before calling prePersist
        filmCategory.prePersiste();

        assertNotNull(filmCategory.getId());
        assertEquals(1, filmCategory.getId().getFilmId());
        assertEquals(2, filmCategory.getId().getCategoryId());
    }

    @Test
    void testLastUpdateField() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        filmCategory.setLastUpdate(timestamp);

        assertEquals(timestamp, filmCategory.getLastUpdate());
    }
}