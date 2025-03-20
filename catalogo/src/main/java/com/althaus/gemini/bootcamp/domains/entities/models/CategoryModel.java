package com.althaus.gemini.bootcamp.domains.entities.models;

import java.sql.Timestamp;
import com.althaus.gemini.bootcamp.domains.entities.Category;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryModel {
	
	@JsonProperty("categoryId")
	@Schema(description = "Identificador de la categoría", example = "1")
	private int categoryId;

	@JsonProperty("lastUpdate")
	@Schema(description = "Fecha de la última actualización", example = "2021-09-01 00:00:00")
	private Timestamp lastUpdate;

	@JsonProperty("name")
	@Schema(description = "Nombre de la categoría", example = "Action")
	private String name;

	public static CategoryModel from(Category source) {
		return new CategoryModel(
			source.getCategoryId(),
			source.getLastUpdate(),
			source.getName()
		);
	}
}
