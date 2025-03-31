
package com.althaus.gemini.bootcamp.domains.core.entities;


import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;



class AbstractEntityTest {

    private TestEntity testEntity;

    @BeforeEach
    void setUp() {
        testEntity = new TestEntity();
    }

    @Test
    void testGetErrors() {
        Set<ConstraintViolation<TestEntity>> errors = testEntity.getErrors();
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
    }

    @Test
    void testGetErrorsFields() {
        Map<String, String> errorsFields = testEntity.getErrorsFields();
        assertNotNull(errorsFields);
        assertEquals(1, errorsFields.size());
        assertTrue(errorsFields.containsKey("name"));
        assertEquals("must not be null", errorsFields.get("name"));
    }

    @Test
    void testGetErrorsMessage() {
        String errorsMessage = testEntity.getErrorsMessage();
        assertNotNull(errorsMessage);
        assertTrue(errorsMessage.contains("name: must not be null."));
    }

    @Test
    void testIsValid() {
        assertFalse(testEntity.isValid());
    }

    @Test
    void testIsInvalid() {
        assertTrue(testEntity.isInvalid());
    }

    // A simple implementation of AbstractEntity for testing purposes
    private static class TestEntity extends AbstractEntity<TestEntity> {
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}