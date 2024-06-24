package com.acme.autoprotracker.User.Domain.Model.Commands;

public record UpdateWorkshopCommand(Long id, String name, String address, String phone, String email, String imageUrl) {
}
