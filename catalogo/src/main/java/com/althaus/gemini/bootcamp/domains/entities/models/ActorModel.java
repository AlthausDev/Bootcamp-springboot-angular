package com.althaus.gemini.bootcamp.domains.entities.models;

import com.althaus.gemini.bootcamp.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ActorModel {

	@JsonProperty("actorId")
	private int actorId;	
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
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
