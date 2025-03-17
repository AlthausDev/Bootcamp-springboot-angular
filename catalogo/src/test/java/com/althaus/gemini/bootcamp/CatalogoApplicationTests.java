package com.althaus.gemini.bootcamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CatalogoApplicationTests {

	@Test
	void contextLoads() {
		CatalogoApplicationTests applicationTests = new CatalogoApplicationTests();
        assertNotNull(applicationTests);
	}

}
