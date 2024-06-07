package com.acme.autoprotracker.User.Domain.Model.Commands;

public record CreateUserCommand(String name, String address, String phone, String email, String imageUrl, String username, String password) {
}
