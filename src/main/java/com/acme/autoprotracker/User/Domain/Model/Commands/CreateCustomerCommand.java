package com.acme.autoprotracker.User.Domain.Model.Commands;

public record CreateCustomerCommand(Long userId, Long workshopId, String name, String address, String phone, String email, String imageUrl) {
}
