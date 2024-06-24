package com.acme.autoprotracker.User.Domain.Model.Commands;

public record CreateWorkshopCommand(Long userId, String name, String address, String phone, String email, String imageUrl) {
}
