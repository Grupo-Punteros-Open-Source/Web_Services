package com.acme.autoprotracker.User.Domain.Model.Commands;

public record UpdateUserCommand(Long id,String username, String password, String name, String address, String phone, String email, String imageUrl) {
}
