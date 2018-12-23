package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.auth.JwtData;
import com.company.boilerplate.services.util.DateHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.mockito.Mock;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtCreatorServiceTests {

    @Mock KeyGeneratorService keyGeneratorService;

    @Mock DateHelper dateHelper;

    private final String SECRET = "your-512-bit-secretyour-512-bit-secretyour-512-bit-secretyour-512-bit-secret";
    private final String ISSUER = "ISSUER";
    private final String AUDIENCE = "AUDIENCE";
    private final int EXPIRATION_IN_MINUTES = 30;

    @Test
    public void create_SimpleJwt_Test() {
        final String expectedToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBRE1JTiIsImF1ZCI6IkFVRElFTkNFIiwiaXNzIjoiSVNTVUVSIiwiaWF0IjoxNTAwMDAwMDAwLCJleHAiOjE1MTAwMDAwMDB9.XjcklWccN84jTZUTbO8o4R_7RQ1ASd9s4j329Y2PHIQPbiPIDmqW3-5lp0amK-5r_an1NJJTg9oWGOwGXunEWw";
        keyGeneratorService = mock(KeyGeneratorService.class);
        dateHelper = mock(DateHelper.class);

        Date currentDate = new Date(1500000000000L);

        Date expirationDate = new Date(1510000000000L);

        when(keyGeneratorService.getJwtSigningKey()).thenReturn(Keys.hmacShaKeyFor(SECRET.getBytes()));
        when(dateHelper.localDateTimeToDate(any())).thenReturn(currentDate);
        when(dateHelper.addMinutes(any(), anyInt())).thenReturn(expirationDate);

        JwtCreatorService jwtCreatorService = new JwtCreatorService(
                ISSUER,
                AUDIENCE,
                EXPIRATION_IN_MINUTES,
                keyGeneratorService,
                dateHelper
                );

        String token = jwtCreatorService.createJwtToken(new JwtData("ADMIN", "ADMIN", Collections.emptyMap()), null);

        assertEquals(expectedToken, token);
    }
}
