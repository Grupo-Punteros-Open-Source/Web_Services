package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record UpdateCustomerResource(String name, String address, String phone, String email, String image_url) {
}
