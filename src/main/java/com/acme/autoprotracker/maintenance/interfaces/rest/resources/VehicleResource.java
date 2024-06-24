package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record VehicleResource(Long id, String plate, String make, String model, Long year, String color, String image_url, Long mileages, Long customer_id) {
}