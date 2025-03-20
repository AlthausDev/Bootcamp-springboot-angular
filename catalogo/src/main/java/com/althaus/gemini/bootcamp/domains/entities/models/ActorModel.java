package com.althaus.gemini.bootcamp.domains.entities.models;

import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Schema(name = "Actor", description = "Modelo de datos de un actor")
public class ActorModel {

	@JsonProperty("actorId")
	private int actorId;	

	@JsonProperty("firstName")
	@Schema(description = "Nombre del actor", example = "John")
	private String firstName;

	@JsonProperty("lastName")
	@Schema(description = "Apellido del actor", example = "Doe")
	private String lastName;
	
	public static ActorModel from(Actor source) {
		return new ActorModel(
			source.getActorId(), 
			source.getFirstName(), 
			source.getLastName()
			);
	}
	
	public static Actor from(ActorModel source) {
		return new Actor(
			source.getActorId(), 
			source.getFirstName(), 
			source.getLastName()
			);
	}
}
