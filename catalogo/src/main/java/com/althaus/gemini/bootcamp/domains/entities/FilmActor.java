package com.althaus.gemini.bootcamp.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * The persistent class for the film_actor database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="film_actor")
@NamedQuery(name="FilmActor.findAll", query="SELECT f FROM FilmActor f")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmActorPK id;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="actor_id", nullable=false, insertable=false, updatable=false)
	private Actor actor;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", nullable=false, insertable=false, updatable=false)
	private Film film;

	public FilmActor(Film film2, Actor actor2) {
		this.film = film2;
		this.actor = actor2;
	}
	
	@PrePersist 
	@PreUpdate
	void prePersiste() {
		if (id == null) {
			setId(new FilmActorPK(film.getFilmId(), actor.getActorId()));
		}
	}

}