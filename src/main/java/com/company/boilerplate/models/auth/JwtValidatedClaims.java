package com.company.boilerplate.models.auth;

public class JwtValidatedClaims {
    private final String subject;
    private final String issuer;
    private final String audience;

    public JwtValidatedClaims(String subject, String issuer, String audience) {
        this.subject = subject;
        this.issuer = issuer;
        this.audience = audience;

    }

    public String getSubject() {
        return subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getAudience() {
        return audience;
    }
}
