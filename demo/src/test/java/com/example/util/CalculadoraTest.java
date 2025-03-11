package com.example.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraTest {

	private Calculadora calculadora;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculadora = new Calculadora();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("Método: Suma")
	class Suma {

		@Nested
		@DisplayName("Casos validos")
		class OK {
			@Test
			@DisplayName("Suma de dos enteros")
			void testSuma() {
				var actual = calculadora.suma(2, 3);
				assertEquals(5, actual);
			}

			@Test
			@DisplayName("Suma de dos reales")
			void testSuma2() {
				var actual = calculadora.suma(2.33, 3.66);
				assertEquals(5.99, actual);

			}
			
			@Test
			@DisplayName("Suma de dos reales: resta")
			void testSuma3() {
				assertEquals(0.3, calculadora.suma(0.2, 0.1));

				assertEquals(0.1, calculadora.suma(1, -0.9));

			}
			
			@ParameterizedTest(name = "{0} + {1} = {2}")
			@CsvSource({"1,2,3", "2,-1,1", "-1,2,1", "-2, -1, -3"})
			@DisplayName("Suma con parametros")
			void testSumaParametrizada(double a, double b, double expected) {
				var actual = calculadora.suma(a, b);

				assertEquals(expected, actual);
			}


		}
	}

	@Nested
	@DisplayName("Casos no validos")
	class KO {
		
		@Test
		@DisplayName("Suma de dos enteros grandes")
		void testSumaInt() {
			var actual = calculadora.suma(Integer.MAX_VALUE, 1);

			assertTrue(actual > 0);
		}
	}

	@Nested
	@DisplayName("Método: Division")
	class Division {

		@Nested
		@DisplayName("Casos validos")
		class OK {
			@Test
			@DisplayName("División de dos enteros")
			void testDivision() {
				assertEquals(0.5, calculadora.division(1.0, 2));
			}

			@Test
			@DisplayName("División por cero")
			void testDivision2() {
				var ex = assertThrows(ArithmeticException.class, () -> calculadora.division(1, 0));

				assertEquals("/ by zero", ex.getMessage());
			}

			@Test
			@DisplayName("División por cero: try")
			void testDivision3() {
				try {
					calculadora.division(1, 0);
					fail("No se ha lanzado la excepción");
				} catch (ArithmeticException ex) {
					assertEquals("/ by zero", ex.getMessage());
				}

			}
		}

		@Nested
		@DisplayName("Casos no validos")
		class KO {

		}

	}

}
