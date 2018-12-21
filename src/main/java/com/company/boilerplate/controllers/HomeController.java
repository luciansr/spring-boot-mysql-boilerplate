package com.company.boilerplate.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/anonymous")
    public String anonymous() {
        return "Anonymous";
    }

    @GetMapping("/logged")
    @PreAuthorize("isAuthenticated()")
    public String logged() {
        return "logged";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
