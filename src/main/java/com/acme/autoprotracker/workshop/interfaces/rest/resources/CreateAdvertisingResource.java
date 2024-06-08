package com.acme.autoprotracker.workshop.interfaces.rest.resources;

public record CreateAdvertisingResource(String name, String image_url, String slogan, String message, Long workshopId) {
}
