package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.entities.Film;

@RepositoryRestResource(exported = false)
public interface FilmRepository extends CoreRepository<Film, Integer>{

    List<Film> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
    List<Film> findByFilmIdGreaterThan(int filmId);
    List<Film> findByTitleContaining(String title);
    List<Film> findByDescriptionContaining(String description);
    List<Film> findByReleaseYear(int releaseYear);
    List<Film> findByRentalDuration(int rentalDuration);
    List<Film> findByRentalRate(double rentalRate);
    List<Film> findByLength(int length);
    List<Film> findByReplacementCost(double replacementCost);
    List<Film> findByRating(String rating);
    List<Film> findByLastUpdate(Timestamp lastUpdate);
}
