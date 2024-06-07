package com.acme.autoprotracker.User.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserData(String name, String address, String phone, String email, String imageUrl) {
    public UserData() {
        this(null, null, null, null, null);
    }

    public UserData(String name, String address, String phone, String email) {
        this(name, address, phone, email, null);
    }

    public String getUserData() {
        return String.format("Name: %s, Address: %s, Phone: %s, Email: %s, ImageUrl: %s", name, address, phone, email, imageUrl);
    }

    public UserData {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or blank");
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Address must not be null or blank");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone must not be null or blank");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email must not be null or blank");
    }
}