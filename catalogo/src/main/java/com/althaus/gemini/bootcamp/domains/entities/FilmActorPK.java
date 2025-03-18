package com.althaus.gemini.bootcamp.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the film_actor database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class FilmActorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="actor_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int actorId;

	@Column(name="film_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int filmId;

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmActorPK)) {
			return false;
		}
		FilmActorPK castOther = (FilmActorPK)other;
		return 
			(this.actorId == castOther.actorId)
			&& (this.filmId == castOther.filmId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.actorId;
		hash = hash * prime + this.filmId;
		
		return hash;
	}

	@Override
	public String toString() {
		return "FilmActorPK [actorId=" + actorId + ", filmId=" + filmId + "]";
	}
}