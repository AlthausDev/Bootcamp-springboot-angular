package com.example.util;

import lombok.Data;

@Data
public class Persona {
	
	private int id = 1;
	private String nombre = "Pepe";
	private String apellido = "Pepe";

	public Persona(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

}
