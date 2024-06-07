package com.acme.autoprotracker.User.Domain.Model.Commands;

public record CreateUserCommand(String username, String password, String name, String address, String phone, String email, String imageUrl) {
}
