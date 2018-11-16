package com.boilerplate.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValuesController {

    @RequestMapping("/api/values")
    public String[] Get() {
        return new String[] {"greeting"};
    }
}
