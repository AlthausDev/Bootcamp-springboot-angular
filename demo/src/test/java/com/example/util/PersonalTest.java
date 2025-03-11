package com.example.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonalTest {

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
	void testCreatePerson() {
		Persona person = new Persona(1, "Pepe");
		
		assertNotNull(person);
		assertAll("Constructor",
				() -> assertEquals(1, person.getId()),
				() -> assertEquals("Pepe", person.getNombre()),
				() -> assertEquals("Pepe", person.getApellido())
				);
		assertEquals(1, person.getId());
		assertEquals("Pepe", person.getNombre());

	}

}
