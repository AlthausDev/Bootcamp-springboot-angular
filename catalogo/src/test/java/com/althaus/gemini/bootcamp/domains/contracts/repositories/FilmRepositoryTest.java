package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import com.althaus.gemini.bootcamp.domains.entities.Film;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;



        @ExtendWith(MockitoExtension.class)
        public class FilmRepositoryTest {

            @Mock
            private FilmRepository filmRepository;

            @Test
            public void testFindByRentalRate() {
                double rentalRate = 2.99;
                Film film1 = new Film();
                film1.setRentalRate(BigDecimal.valueOf(rentalRate));
                Film film2 = new Film();
                film2.setRentalRate(BigDecimal.valueOf(rentalRate));

                List<Film> expectedFilms = Arrays.asList(film1, film2);

                when(filmRepository.findByRentalRate(rentalRate)).thenReturn(expectedFilms);

                List<Film> actualFilms = filmRepository.findByRentalRate(rentalRate);

                assertEquals(expectedFilms, actualFilms);
            }

            @Test
            public void testFindByTitleContaining() {
                String title = "Star";
                Film film1 = new Film();
                film1.setTitle("Star Wars");
                Film film2 = new Film();
                film2.setTitle("Star Trek");

                List<Film> expectedFilms = Arrays.asList(film1, film2);

                when(filmRepository.findByTitleContaining(title)).thenReturn(expectedFilms);

                List<Film> actualFilms = filmRepository.findByTitleContaining(title);

                assertEquals(expectedFilms, actualFilms);
            }

            @Test
            public void testFindByReleaseYear() {
                int releaseYear = 1999;
                Film film1 = new Film();
                film1.setReleaseYear((short) releaseYear);
                Film film2 = new Film();
                film2.setReleaseYear((short) releaseYear);

                List<Film> expectedFilms = Arrays.asList(film1, film2);

                when(filmRepository.findByReleaseYear(releaseYear)).thenReturn(expectedFilms);

                List<Film> actualFilms = filmRepository.findByReleaseYear(releaseYear);

                assertEquals(expectedFilms, actualFilms);
            }

            @Test
            public void testFindByLastUpdate() {
                Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
                Film film1 = new Film();
                film1.setLastUpdate(lastUpdate);
                Film film2 = new Film();
                film2.setLastUpdate(lastUpdate);

                List<Film> expectedFilms = Arrays.asList(film1, film2);

                when(filmRepository.findByLastUpdate(lastUpdate)).thenReturn(expectedFilms);

                List<Film> actualFilms = filmRepository.findByLastUpdate(lastUpdate);

                assertEquals(expectedFilms, actualFilms);
            }
        }
