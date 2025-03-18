package com.althaus.gemini.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
	private long id;
	private String nombre, correo, ip;
}
