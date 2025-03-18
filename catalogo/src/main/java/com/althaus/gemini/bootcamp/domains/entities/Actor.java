package com.althaus.gemini.bootcamp.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the actor database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;
	
	@Column(name="first_name", nullable=false, length=45)
	@NotBlank
	@Size(max = 45, min = 5)
	@Pattern(regexp= "^[A-Za-z]*$", message = "El nombre debe estar compuesto por letras")
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=45)
	@NotBlank
	@Size(max = 45, min = 5)
	@Pattern(regexp= "^[A-Za-z]*$", message = "El apellido debe estar compuesto por letras")
	private String lastName;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	@PastOrPresent
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<FilmActor> filmActors;
	
	
	public Actor(int actorId) {
		this.actorId = actorId;
	}

	public Actor(int actorId, String firstName, String lastName) {
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		}


	@Override
	public int hashCode() {
		return Objects.hash(actorId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return actorId == other.actorId;
	}


	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}
	
	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

}