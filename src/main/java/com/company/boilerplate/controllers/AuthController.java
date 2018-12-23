package com.company.boilerplate.controllers;

import com.company.boilerplate.services.auth.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth/token")
    public String getToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        return authenticationService.authenticateUser(username, password);
    }
}
