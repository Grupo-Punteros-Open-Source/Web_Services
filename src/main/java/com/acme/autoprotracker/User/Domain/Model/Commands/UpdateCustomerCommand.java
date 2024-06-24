package com.acme.autoprotracker.User.Domain.Model.Commands;

public record UpdateCustomerCommand(Long id, String name, String address, String phone, String email, String imageUrl) {
}
