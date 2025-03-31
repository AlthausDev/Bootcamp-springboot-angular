

package com.althaus.gemini.bootcamp.utils.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DuplicateKeyExceptionTest {

    @Test
    void testDefaultConstructor() {
        DuplicateKeyException exception = new DuplicateKeyException();
        assertEquals("Duplicate key", exception.getMessage());
    }

    @Test
    void testConstructorWithMessage() {
        String customMessage = "Custom duplicate key message";
        DuplicateKeyException exception = new DuplicateKeyException(customMessage);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Cause of the exception");
        DuplicateKeyException exception = new DuplicateKeyException(cause);
        assertEquals("Duplicate key", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String customMessage = "Custom duplicate key message";
        Throwable cause = new RuntimeException("Cause of the exception");
        DuplicateKeyException exception = new DuplicateKeyException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithAllParameters() {
        String customMessage = "Custom duplicate key message";
        Throwable cause = new RuntimeException("Cause of the exception");
        DuplicateKeyException exception = new DuplicateKeyException(customMessage, cause, true, false);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertFalse(exception.getStackTrace().length == 0); // Writable stack trace is false
    }
}