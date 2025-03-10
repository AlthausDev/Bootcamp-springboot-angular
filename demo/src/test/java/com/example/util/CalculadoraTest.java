package com.example.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Suma de dos enteros")
	void testSuma() {
		Calculadora calculadora = new Calculadora();		
		var actual = calculadora.suma(2,3);
		
		assertEquals(5, actual);
	}
	
	@Test
	@DisplayName("Suma de dos decimales")
	void testSuma2() {
		Calculadora calculadora = new Calculadora();		
		var actual = calculadora.suma(2.33, 3.66);
		
		assertEquals(5.99, actual);

	}
	
	@Test
	@DisplayName("Suma de dos decimales: resta")
	void testSuma3() {
		Calculadora calculadora = new Calculadora();		
		
		assertEquals(0.3, calculadora.suma(0.2, 0.1));

		assertEquals(0.1, calculadora.suma(1, -0.9));

	}
	
	@Test
	@DisplayName("Suma de dos enteros grandes")
	void testSuma4() {
		Calculadora calculadora = new Calculadora();		
		
		var actual = calculadora.suma(Integer.MAX_VALUE, 1);
		
		assertTrue(actual > 0);

	}
}
