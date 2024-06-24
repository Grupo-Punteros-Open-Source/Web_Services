package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CreateCustomerResource(Long userId,Long workshopId ,String name, String address, String phone, String email, String imageUrl) {
}
