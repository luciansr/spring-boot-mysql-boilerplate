package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.auth.JwtValidatedClaims;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtValidatorServiceTests {

    @Mock
    KeyGeneratorService keyGeneratorService;

    private final String SECRET = "your-512-bit-secretyour-512-bit-secretyour-512-bit-secretyour-512-bit-secret";
    private final String ISSUER = "ISSUER";
    private final String AUDIENCE = "AUDIENCE";

    @Test
    public void validate_SimpleJwt_Test() {
        final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBRE1JTiIsImF1ZCI6IkFVRElFTkNFIiwiaXNzIjoiSVNTVUVSIiwiaWF0IjoxNTAwMDAwMDAwfQ.wYlNW0J3XkiaNYE2rH9shzCUy8Ak1UHZREBYp3HNp8DMWXhxurjYn-9eOSQc1oFqBWW9Z-9sEel-x0FLfeoL5w";
        keyGeneratorService = mock(KeyGeneratorService.class);

        when(keyGeneratorService.getJwtValidationKey()).thenReturn(Keys.hmacShaKeyFor(SECRET.getBytes()));

        JwtValidatorService jwtCreatorService = new JwtValidatorService(
                AUDIENCE,
                ISSUER,
                keyGeneratorService
        );

        JwtValidatedClaims claims = jwtCreatorService.validateJwt(token);

        assertEquals("ADMIN", claims.getSubject());
        assertEquals("AUDIENCE", claims.getAudience());
        assertEquals("ISSUER", claims.getIssuer());
    }
}
