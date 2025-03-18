package com.althaus.gemini.bootcamp.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the film_category database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class FilmCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="film_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int filmId;

	@Column(name="category_id", insertable=false, updatable=false, unique=true, nullable=false)
	private byte categoryId;

	
	public FilmCategoryPK(int filmId2, int categoryId2) {
		this.filmId = filmId2;
		this.categoryId = (byte) categoryId2;
    }
 
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmCategoryPK)) {
			return false;
		}
		FilmCategoryPK castOther = (FilmCategoryPK)other;
		return 
			(this.filmId == castOther.filmId)
			&& (this.categoryId == castOther.categoryId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.filmId;
		hash = hash * prime + ((int) this.categoryId);
		
		return hash;
	}
}