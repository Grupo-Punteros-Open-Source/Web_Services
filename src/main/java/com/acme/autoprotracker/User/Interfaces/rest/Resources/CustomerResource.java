package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CustomerResource(Long id, Long user_id, Long workshopId , String name, String address, String phone, String email, String image_url) {
}
