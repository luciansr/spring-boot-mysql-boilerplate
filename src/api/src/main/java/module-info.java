module boilerplate.api {
    requires boilerplate.repository;
    requires boilerplate.models;
    requires boilerplate.services;

    requires spring.core;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;

    exports com.company.boilerplate.api.controllers;
}