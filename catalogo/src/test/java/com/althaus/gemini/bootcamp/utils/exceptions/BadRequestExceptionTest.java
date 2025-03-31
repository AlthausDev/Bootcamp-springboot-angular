

package com.althaus.gemini.bootcamp.utils.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class BadRequestExceptionTest {

    @Test
    void testBadRequestExceptionWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");

        BadRequestException exception = new BadRequestException(message, cause);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}