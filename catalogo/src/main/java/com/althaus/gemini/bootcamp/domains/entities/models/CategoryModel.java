package com.althaus.gemini.bootcamp.domains.entities.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryModel {
	
	private int categoryId;
	private Timestamp lastUpdate;
	private String name;
}
