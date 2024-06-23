package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CustomerResource(Long id, Long userId,Long workshopId ,String name, String address, String phone, String email, String imageUrl) {
}
