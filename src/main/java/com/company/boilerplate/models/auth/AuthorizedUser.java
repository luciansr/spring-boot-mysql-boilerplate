package com.company.boilerplate.models.auth;

public class AuthorizedUser {
    private final String username;

    public AuthorizedUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
