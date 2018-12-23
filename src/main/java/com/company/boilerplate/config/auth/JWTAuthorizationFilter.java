package com.company.boilerplate.config.auth;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.Verification;
import com.company.boilerplate.models.auth.AuthorizedUser;
import com.company.boilerplate.services.auth.AuthorizationService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthorizationService authorizationService;
    private final String AUTHORIZATION_HEADER;
    private final String TOKEN_PREFIX;

    public JWTAuthorizationFilter(AuthenticationManager authManager,
                                  String authorizationHeader,
                                  String tokenPrefix,
                                  AuthorizationService authorizationService) {
        super(authManager);
        this.AUTHORIZATION_HEADER = authorizationHeader;
        this.TOKEN_PREFIX = tokenPrefix;
        this.authorizationService = authorizationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(AUTHORIZATION_HEADER);

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(token, res);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletResponse response) {
        AuthorizedUser user = authorizationService.authorizeUser(token.replace(TOKEN_PREFIX, ""));
        if(user == null) return null;

//        response.addCookie(new Cookie("teste", "asd"));
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, new ArrayList<>());
    }
}