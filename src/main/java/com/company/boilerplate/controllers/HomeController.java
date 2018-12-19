package com.company.boilerplate.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/anonymous")
    public String anonymous() {
        return "Anonymous";
    }

    @RequestMapping("/logged")
    @PreAuthorize("isAuthenticated()")
    public String logged() {
        return "logged";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
