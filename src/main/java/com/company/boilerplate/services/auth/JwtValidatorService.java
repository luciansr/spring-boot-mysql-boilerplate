package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.auth.JwtValidatedClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtValidatorService {
    private final String AUDIENCE;
    private final String ISSUER;
    private final KeyGeneratorService keyGeneratorService;

    public JwtValidatorService(
            @Value("${jwt.config.audience}") final String audience,
            @Value("${jwt.config.issuer}") final String issuer,
            KeyGeneratorService keyGeneratorService) {

        this.AUDIENCE = audience;
        this.ISSUER = issuer;
        this.keyGeneratorService = keyGeneratorService;
    }

    public JwtValidatedClaims validateJwt(String token) {
        try {
              Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(keyGeneratorService.getJwtValidationKey())
                    .requireAudience(AUDIENCE)
                    .requireIssuer(ISSUER)
                    .parseClaimsJws(token.strip());

            return getJwtValidatedClaims(claims);
        } catch (JwtException e) {
            //don't trust the JWT!
            return null;
        }
    }

    private JwtValidatedClaims getJwtValidatedClaims(Jws<Claims> claims) {
        if(claims == null) return null;

        return new JwtValidatedClaims(
                claims.getBody().getSubject(),
                claims.getBody().getIssuer(),
                claims.getBody().getAudience());
    }
}
