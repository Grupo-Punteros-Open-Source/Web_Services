package com.acme.autoprotracker.User.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserAuthentication(String username, String password) {
    public UserAuthentication() {
        this(null, null);
    }

    public String getCredentials() {
        return String.format("Username: %s, Password: %s", username, password);
    }

    public UserAuthentication {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("Username must not be null or blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password must not be null or blank");
    }
}