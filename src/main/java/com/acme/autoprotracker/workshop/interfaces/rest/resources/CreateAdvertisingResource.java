package com.acme.autoprotracker.workshop.interfaces.rest.resources;

public record CreateAdvertisingResource(String comName, String comImage, Long workshop_id, String imageUrl, String slogan, String priceMsg, String disMsg, String repairMsg) {
}
