package faddy.backend.jwt.util;

import faddy.backend.auth.api.response.AuthTokensResponse;
import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.auth.service.AuthTokensGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthTokensGeneratorTest {

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthTokensGenerator authTokensGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate() {
        // Given
        String username = "testUser";
        String mockToken = "mockToken";
        when(jwtUtil.generate(anyString(), any())).thenReturn(mockToken);

        // When
        AuthTokensResponse tokens = authTokensGenerator.generate(username);

        System.out.println("tokens = " + tokens.toString());

        // Then
        assertNotNull(tokens);
        assertEquals(mockToken, tokens.getAccessToken());
        assertEquals(mockToken, tokens.getRefreshToken());
    }

    @Test
    void isValidToken() {
        // Given
        String token = "validToken";
        when(jwtUtil.validateToken(token)).thenReturn(true);

        // When
        boolean isValid = authTokensGenerator.isValidToken(token);

        // Then
        assertTrue(isValid);
    }

    @Test
    void extractUsername() {
        // Given
        String token = "validToken";
        String username = "testUser";
        when(jwtUtil.getUsername(token)).thenReturn(username);

        // When
        String extractedUsername = authTokensGenerator.extractUsername(token);

        // Then
        assertEquals(username, extractedUsername);
    }
}
