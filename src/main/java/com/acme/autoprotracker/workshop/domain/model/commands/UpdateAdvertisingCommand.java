package com.acme.autoprotracker.workshop.domain.model.commands;

public record UpdateAdvertisingCommand(Long id,String name, String image_url, String slogan, String message, Long workshopId) {
}
