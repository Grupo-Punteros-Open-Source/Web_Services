package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CreateCustomerResource(Long userId, Long workshop_id, String name, String address, String phone, String email, String image_url) {
}
