
package com.althaus.gemini.bootcamp.security;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;




public class JWTAuthorizationFilterTest {

    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    private String secret;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        secret = Base64.getEncoder().encodeToString("test-public-key".getBytes());
        jwtAuthorizationFilter = new JWTAuthorizationFilter(secret);
    }

    @Test
    public void testDoFilterInternal_ValidToken() throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Arrange
        String token = JWT.create()
                .withIssuer("MicroserviciosJWT")
                .withAudience("authorization")
                .withClaim("username", "testUser")
                .withClaim("authorities", String.join(",", "ROLE_USER"))
                .sign(Algorithm.HMAC256("test-secret")); // Mock signing for test purposes
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        // Act
        jwtAuthorizationFilter.doFilterInternal(request, response, chain);

        // Assert
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("testUser", SecurityContextHolder.getContext().getAuthentication().getName());
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer invalid-token");

        // Act
        jwtAuthorizationFilter.doFilterInternal(request, response, chain);

        // Assert
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(response, times(1)).sendError(eq(HttpServletResponse.SC_FORBIDDEN), anyString());
    }

    @Test
    public void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtAuthorizationFilter.doFilterInternal(request, response, chain);

        // Assert
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_InvalidKeySpec() throws ServletException, IOException {
        // Arrange
        jwtAuthorizationFilter = new JWTAuthorizationFilter("invalid-secret");
        when(request.getHeader("Authorization")).thenReturn("Bearer some-token");

        // Act
        jwtAuthorizationFilter.doFilterInternal(request, response, chain);

        // Assert
        verify(response, times(1)).sendError(eq(HttpServletResponse.SC_INTERNAL_SERVER_ERROR), anyString());
    }
}