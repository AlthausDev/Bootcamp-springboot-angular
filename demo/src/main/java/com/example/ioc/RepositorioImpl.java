package com.example.ioc;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorioImpl implements Repositorio {

	public RepositorioImpl(Configuracion config) {
		
	}

	@Override
	public void guardar() {
		System.err.println("Simulacion de guardado");
		
	}
}
