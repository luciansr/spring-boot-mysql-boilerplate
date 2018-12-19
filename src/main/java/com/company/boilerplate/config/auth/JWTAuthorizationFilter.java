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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private String AUTHORIZATION_HEADER;
    private final String TOKEN_PREFIX;
    private final String SECRET;
    private final String AUDIENCE;
    private final String ISSUER;

    public JWTAuthorizationFilter(AuthenticationManager authManager,
                                  String authorizationHeader,
                                  String tokenPrefix,
                                  String secret,
                                  String audience,
                                  String issuer) {
        super(authManager);
        this.AUTHORIZATION_HEADER = authorizationHeader;
        this.TOKEN_PREFIX = tokenPrefix;
        this.SECRET = secret;
        this.AUDIENCE = audience;
        this.ISSUER = issuer;
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

        if (token != null) {
            // parse the token.
            try {
                Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

                Jws<Claims> claims =  Jwts.parser()
                        .setSigningKey(key)
                        .requireAudience(AUDIENCE)
                        .requireIssuer(ISSUER)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "").strip());

//                claims.getBody().g


                System.out.println("claims = " + claims);
                //OK, we can trust this JWT
//                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
//                        .build()
//                        .verify(token.replace(TOKEN_PREFIX, "").strip());
//
//                String user = decodedJWT.getSubject();
                return new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), null, new ArrayList<>());

            } catch (JwtException e) {
                System.out.println("e.getMessage() = " + e.getMessage());
                //response.setStatus(401);
                response.addCookie(new Cookie("teste", "asd"));
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