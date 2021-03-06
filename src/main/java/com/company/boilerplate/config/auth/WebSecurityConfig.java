package com.company.boilerplate.config.auth;


import com.company.boilerplate.services.auth.AuthorizationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.config.authorization-header}")
    private String AUTHORIZATION_HEADER;
    @Value("${jwt.config.token-prefix}")
    private String TOKEN_PREFIX;

    private final AuthorizationService authorizationService;

    public WebSecurityConfig(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),
                        AUTHORIZATION_HEADER,
                        TOKEN_PREFIX,
                        authorizationService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
}