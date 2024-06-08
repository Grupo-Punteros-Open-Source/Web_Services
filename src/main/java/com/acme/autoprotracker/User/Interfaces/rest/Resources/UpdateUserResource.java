package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record UpdateUserResource(String name, String address, String phone, String email, String imageUrl, String username, String password) {
}