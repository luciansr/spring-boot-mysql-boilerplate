package com.company.boilerplate.api.controllers;

import com.company.boilerplate.models.Model1;
import com.company.boilerplate.repository.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/object", method = GET)
    public Model1 Object() {
        return new Model1();
    }

    @RequestMapping(value = "/object2", method = GET)
    public User Object1() {
        return new User();
    }
}
