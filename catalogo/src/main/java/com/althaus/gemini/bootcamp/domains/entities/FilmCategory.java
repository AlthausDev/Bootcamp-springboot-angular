package com.althaus.gemini.bootcamp.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * The persistent class for the film_category database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="film_category")
@NamedQuery(name="FilmCategory.findAll", query="SELECT f FROM FilmCategory f")
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmCategoryPK id;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false, insertable=false, updatable=false)
	private Category category;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", nullable=false, insertable=false, updatable=false)
	private Film film;

	public FilmCategory(Film film2, Category item) {
		this.film = film2;
		this.category = item;
	}

	@PrePersist
	@PreUpdate
	void prePersiste() {
		if (id == null) {
			setId(new FilmCategoryPK(film.getFilmId(), category.getCategoryId()));
		}
	}

}