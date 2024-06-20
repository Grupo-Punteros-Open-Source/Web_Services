package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CreateCustomerResource(Long userId, String name, String address, String phone, String email, String imageUrl) {
}
