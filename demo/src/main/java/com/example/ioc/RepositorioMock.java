package com.example.ioc;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMock implements Repositorio {


	public RepositorioMock(Configuracion config) {

	}


	@Override
	public void guardar() {
		System.err.println("Simulacion de guardado Mock");

	}
}
