

package com.althaus.gemini.bootcamp.utils.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;


class ApiExceptionHandlerTest {

    private final ApiExceptionHandler handler = new ApiExceptionHandler();

    @Test
    void testDefaultResponse() {
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        ProblemDetail result = handler.defaultResponse(exception);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
    }

    @Test
    void testNotFoundRequest() {
        NotFoundException exception = new NotFoundException("Resource not found");
        ProblemDetail result = handler.notFoundRequest(exception);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
    }

    @Test
    void testBadRequest() {
        BadRequestException exception = new BadRequestException("Bad request");
        ProblemDetail result = handler.badRequest(exception);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
        assertEquals("Bad request", result.getDetail());
    }

    @Test
    void testInvalidDataWithInvalidDataException() {
        InvalidDataException exception = mock(InvalidDataException.class);
        when(exception.hasErrors()).thenReturn(true);
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("errorKey", "Invalid data details");
        when(exception.getErrors()).thenReturn(errorMap);

        ProblemDetail result = handler.invalidData(exception);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
        assertEquals("Datos invalidos", result.getDetail());
        assertEquals("Invalid data details", result.getProperties().get("errors"));
    }

    @Test
    void testInvalidDataWithBindException() {
        BindException exception = new BindException(new Object(), "testObject");
        exception.addError(new FieldError("testObject", "field1", "Field1 is invalid"));

        ProblemDetail result = handler.invalidData(exception);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
        assertEquals("Datos invalidos", result.getDetail());
        HashMap<String, String> errors = (HashMap<String, String>) result.getProperties().get("errors");
        assertEquals("Field1 is invalid", errors.get("field1"));
    }

    @Test
    void testUnknow() {
        Exception exception = new Exception("Unknown error");
        ProblemDetail result = handler.unknow(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getStatus());
        assertEquals("Unknown error", result.getDetail());
    }

    @Test
    void testDefaultResponseWithHttpRequestMethodNotSupportedException() {
        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("POST");
        ProblemDetail result = handler.defaultResponse(exception);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), result.getStatus());
    }
}