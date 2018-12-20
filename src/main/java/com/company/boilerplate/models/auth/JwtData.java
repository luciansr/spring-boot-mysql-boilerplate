package com.company.boilerplate.models.auth;

import java.util.Map;

public class JwtData {
    public final String username;
    public final String role;
    public final Map<String, Object> claims;

    public JwtData(String username, String role,  Map<String, Object> claims) {
        this.username = username;
        this.role = role;
        this.claims = claims;
    }
}
