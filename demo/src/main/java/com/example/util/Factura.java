package com.example.util;

public class Factura {
	Calculadora calculadora;

	public Factura(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public double calcularTotal(int cantidad, double precio) {
		return calculadora.suma(cantidad, precio);

	}
}
