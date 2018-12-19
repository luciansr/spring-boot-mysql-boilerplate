package com.company.boilerplate.config.auth;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.Verification;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private String HEADER_STRING;
    private final String TOKEN_PREFIX;
    private final String SECRET;

    public JWTAuthorizationFilter(AuthenticationManager authManager,
                                  String headerString,
                                  String tokenPrefix,
                                  String secret) {
        super(authManager);
        this.HEADER_STRING = headerString;
        this.TOKEN_PREFIX = tokenPrefix;
        this.SECRET = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String token = req.getHeader(HEADER_STRING);

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (token != null) {
            // parse the token.
            try {
                Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

                Jws<Claims> claims =  Jwts.parser()
                        .setSigningKey(key)
                        .requireAudience("AUDIENCE")
                        .requireIssuer("ISSUER")
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "").strip());

//                claims.getBody().g


                System.out.println("claims = " + claims);
                //OK, we can trust this JWT
//                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
//                        .build()
//                        .verify(token.replace(TOKEN_PREFIX, "").strip());
//
//                String user = decodedJWT.getSubject();
                return new UsernamePasswordAuthenticationToken("ADMIN", null, new ArrayList<>());

            } catch (JwtException e) {
                System.out.println("e.getMessage() = " + e.getMessage());
                //don't trust the JWT!
            }



//            if (user != null) {
//
//            }
            return null;
        }
        return null;
    }
}