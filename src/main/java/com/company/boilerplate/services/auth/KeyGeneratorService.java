package com.company.boilerplate.services.auth;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class KeyGeneratorService {
    private final String SECRET;

    public KeyGeneratorService(@Value("${jwt.config.secret}") final String secret) {
        this.SECRET = secret;
    }

    public Key getJwtSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Key getJwtValidationKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}
