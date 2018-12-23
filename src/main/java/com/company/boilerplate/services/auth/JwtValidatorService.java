package com.company.boilerplate.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class JwtValidatorService {
    private final String AUDIENCE;
    private final String ISSUER;
    private final KeyGeneratorService keyGeneratorService;

    public JwtValidatorService(
            @Value("jwt.config.audience") final String audience,
            @Value("jwt.config.issuer") final String issuer,
            KeyGeneratorService keyGeneratorService) {

        this.AUDIENCE = audience;
        this.ISSUER = issuer;
        this.keyGeneratorService = keyGeneratorService;
    }

    public Jws<Claims> validateJwt(String token) {
        try {
            return  Jwts.parser()
                    .setSigningKey(keyGeneratorService.getJwtValidationKey())
                    .requireAudience(AUDIENCE)
                    .requireIssuer(ISSUER)
                    .parseClaimsJws(token.strip());
        } catch (JwtException e) {
            //don't trust the JWT!
            return null;
        }
    }


}
