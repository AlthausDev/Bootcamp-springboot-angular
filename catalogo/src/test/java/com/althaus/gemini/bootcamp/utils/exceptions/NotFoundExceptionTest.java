package com.althaus.gemini.bootcamp.utils.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


class NotFoundExceptionTest {

    @Test
    void testNotFoundExceptionWithMessage() {
        String message = "Custom not found message";
        NotFoundException exception = new NotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testNotFoundExceptionWithoutMessage() {
        NotFoundException exception = new NotFoundException();
        assertEquals("Not found", exception.getMessage());
    }

    @Test
    void testNotFoundExceptionWithCause() {
        Throwable cause = new RuntimeException("Cause of the exception");
        NotFoundException exception = new NotFoundException(cause);
        assertEquals("Not found", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testNotFoundExceptionWithMessageAndCause() {
        String message = "Custom not found message";
        Throwable cause = new RuntimeException("Cause of the exception");
        NotFoundException exception = new NotFoundException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testNotFoundExceptionWithAllParameters() {
        String message = "Custom not found message";
        Throwable cause = new RuntimeException("Cause of the exception");
        NotFoundException exception = new NotFoundException(message, cause, true, false);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}