package com.example.util;

import com.example.ioc.Repositorio;

public class Factura {
	Calculadora calculadora;
	Repositorio repositorio;

	public Factura(Calculadora calculadora, Repositorio respositorio) {
		this.calculadora = calculadora;
		this.repositorio = repositorio;
	}

	public double calcularTotal(int cantidad, double precio) {
		return calculadora.suma(cantidad, precio);

	}
	
	public void emitir() {
		repositorio.guardar();
	}
}
