package com.company.boilerplate.models;

public class ApplicationUser {
    private long id;
    private String username;
    private String password;
    private String role;

    public ApplicationUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}