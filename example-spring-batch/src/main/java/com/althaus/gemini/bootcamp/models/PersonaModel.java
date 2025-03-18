package com.althaus.gemini.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaModel {
	private long id;
	private String nombre, apellidos, correo, sexo, ip;
}
