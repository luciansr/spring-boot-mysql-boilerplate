package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.auth.JwtData;
import com.company.boilerplate.services.util.DateHelper;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JwtCreatorService {

    private final String ISSUER;
    private final String AUDIENCE;
    private final int EXPIRATION_IN_MINUTES;
    private final KeyGeneratorService keyGeneratorService;
    private final DateHelper dateHelper;

    public JwtCreatorService(@Value("${jwt.config.issuer}") final String issuer,
                             @Value("${jwt.config.audience}") final String audience,
                             @Value("${jwt.config.expiration-in-minutes}") final int expirationInMinutes,
                             KeyGeneratorService keyGeneratorService,
                             DateHelper dateHelper) {
        this.ISSUER = issuer;
        this.AUDIENCE = audience;
        this.EXPIRATION_IN_MINUTES = expirationInMinutes;
        this.keyGeneratorService = keyGeneratorService;
        this.dateHelper = dateHelper;
    }

    public String createJwtToken(final JwtData jwtData, LocalDateTime issuedAt) {
        return Jwts.builder()
                .setSubject(jwtData.username)
                .addClaims(jwtData.claims)
                .setAudience(AUDIENCE)
                .setIssuer(ISSUER)
                .setIssuedAt(dateHelper.localDateTimeToDate(issuedAt))
                .setExpiration(dateHelper.addMinutes(issuedAt, EXPIRATION_IN_MINUTES))
                .signWith(keyGeneratorService.getJwtSigningKey()).compact();
    }
}
