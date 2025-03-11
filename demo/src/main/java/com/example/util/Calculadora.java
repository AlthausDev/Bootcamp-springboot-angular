package com.example.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
	
	public double suma(double a, double b) {
		return roundIEEE745(a + b);
	}
	
	public int suma(int a, int b) {
		return a + b;
	}
	
	public int division(int a, int b) {
		try {
			return a / b;
		} catch(ArithmeticException ex) {
			ex.getMessage();
			return b ;
		}
	}
	
	public double division(double a, double b) {
		return roundIEEE745(a/b);
	}
	
	private double roundIEEE745(double o) {
		return BigDecimal.valueOf(o)
				.setScale(16, RoundingMode.HALF_UP)
				.doubleValue();
	}
}