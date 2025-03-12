package com.example.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import com.example.ioc.Repositorio;
import com.example.test.utils.Smoke;

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
			@Smoke
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
	@Disabled
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
		
		@Nested
		@DisplayName("Suplanta")
		class Suplantaciones {
			@Test
			void suplanta() {
				var calculadora = mock(Calculadora.class);
				
				when(calculadora.suma(anyInt(), anyInt()))
					.thenReturn(3)
					.thenReturn(5);

				var actual = calculadora.suma(2, 2);
				assertEquals(3, actual);
				assertEquals(5, calculadora.suma(2, 2));
				assertEquals(5, calculadora.suma(7,3));
			}
			
			@Test
			void suplanta2() {
				var calculadora = mock(Calculadora.class);
				var repositorio = mock(Repositorio.class);
				
				when(calculadora.suma(anyInt(), anyInt())).thenReturn(4);
				doNothing().when(repositorio).guardar();
				
				Factura factura = new Factura(calculadora, repositorio);
				var actual = factura.calcularTotal(2, 2);
				
				factura.emitir();
				
				assertEquals(4, actual);
				verify(calculadora).suma(2, 2);
			}
			
			@Test
			void suplanta3() {
				Calculadora calculadora = mock(Calculadora.class);
				Repositorio repositorio = mock(Repositorio.class);
			}
			
			@Test
			void Integracion() {
				var obj = new Factura(new Calculadora(), null);
				var actual = obj.calcularTotal(2, 2);
				assertEquals(4, actual);
			}
		}
	}
}
