package com.acme.autoprotracker.workshop.domain.model.commands;

public record CreateAdvertisingCommand(String name, String image_url, String slogan, String message, Long workshopId) {
}
