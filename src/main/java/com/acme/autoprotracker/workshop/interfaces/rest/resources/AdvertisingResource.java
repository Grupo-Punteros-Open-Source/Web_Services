package com.acme.autoprotracker.workshop.interfaces.rest.resources;

public record AdvertisingResource(Long id, String comName, String comImage, Long workshop_id, String imageUrl, String slogan, String priceMsg, String disMsg, String repairMsg) {
}
