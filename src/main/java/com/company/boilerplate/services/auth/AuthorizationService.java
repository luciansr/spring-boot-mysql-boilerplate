package com.company.boilerplate.services.auth;

import com.company.boilerplate.models.auth.AuthorizedUser;
import com.company.boilerplate.models.auth.JwtValidatedClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final JwtValidatorService jwtValidatorService;

    public AuthorizationService(JwtValidatorService jwtValidatorService) {
        this.jwtValidatorService = jwtValidatorService;
    }

    public AuthorizedUser authorizeUser(String jwtToken) {
        JwtValidatedClaims claims = jwtValidatorService.validateJwt(jwtToken);
        return getAuthorizedUserFromClaims(claims);
    }

    private AuthorizedUser getAuthorizedUserFromClaims(JwtValidatedClaims claims) {
        if(claims == null) return null;
        return new AuthorizedUser(claims.getSubject());
    }

}
