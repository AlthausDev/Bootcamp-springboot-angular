package com.althaus.gemini.bootcamp.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.althaus.gemini.bootcamp.domains.core.entities.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the language database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language extends AbstractEntity<Language> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int languageId;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	@Column(nullable=false, length=20)
	private String name;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language")
	@JsonIgnore
	private List<Film> films;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="languageVO")
	@JsonIgnore
	private List<Film> filmsVO;
	
	public Language(int languageId) {
		this.languageId = languageId;
	}

    public Language(int languageId, String name) {
		this.languageId = languageId;
		this.name = name;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setLanguage(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setLanguage(null);

		return film;
	}

	
	public Film addFilmsVO(Film filmsVO) {
		getFilmsVO().add(filmsVO);
		filmsVO.setLanguageVO(this);

		return filmsVO;
	}

	public Film removeFilmsVO(Film filmsVO) {
		getFilmsVO().remove(filmsVO);
		filmsVO.setLanguageVO(null);

		return filmsVO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Language o)
			return languageId == o.languageId;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", name=" + name + "]";
	}
}