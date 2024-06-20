package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CustomerResources(Long id, Long userId, String name, String address, String phone, String email, String imageUrl) {
}
