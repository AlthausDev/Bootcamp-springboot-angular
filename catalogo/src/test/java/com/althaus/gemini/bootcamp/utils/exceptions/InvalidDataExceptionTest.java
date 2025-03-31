

package com.althaus.gemini.bootcamp.utils.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class InvalidDataExceptionTest {

    @Test
    void testDefaultConstructor() {
        InvalidDataException exception = new InvalidDataException();
        assertEquals("Invalid data", exception.getMessage());
        assertNull(exception.getErrors());
    }

    @Test
    void testConstructorWithMessage() {
        String customMessage = "Custom error message";
        InvalidDataException exception = new InvalidDataException(customMessage);
        assertEquals(customMessage, exception.getMessage());
        assertNull(exception.getErrors());
    }

    @Test
    void testConstructorWithErrors() {
        Map<String, String> errors = Map.of("field1", "error1", "field2", "error2");
        InvalidDataException exception = new InvalidDataException(errors);
        assertEquals("Invalid data", exception.getMessage());
        assertNotNull(exception.getErrors());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testConstructorWithMessageAndErrors() {
        String customMessage = "Custom error message";
        Map<String, String> errors = Map.of("field1", "error1", "field2", "error2");
        InvalidDataException exception = new InvalidDataException(customMessage, errors);
        assertEquals(customMessage, exception.getMessage());
        assertNotNull(exception.getErrors());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Cause of the exception");
        InvalidDataException exception = new InvalidDataException(cause);
        assertEquals("Invalid data", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNull(exception.getErrors());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String customMessage = "Custom error message";
        Throwable cause = new RuntimeException("Cause of the exception");
        InvalidDataException exception = new InvalidDataException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNull(exception.getErrors());
    }

    @Test
    void testConstructorWithMessageCauseAndErrors() {
        String customMessage = "Custom error message";
        Throwable cause = new RuntimeException("Cause of the exception");
        Map<String, String> errors = Map.of("field1", "error1", "field2", "error2");
        InvalidDataException exception = new InvalidDataException(customMessage, cause, errors);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNotNull(exception.getErrors());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testHasErrors() {
        InvalidDataException exceptionWithoutErrors = new InvalidDataException();
        assertFalse(exceptionWithoutErrors.hasErrors());

        Map<String, String> errors = Map.of("field1", "error1");
        InvalidDataException exceptionWithErrors = new InvalidDataException(errors);
        assertTrue(exceptionWithErrors.hasErrors());
    }
}