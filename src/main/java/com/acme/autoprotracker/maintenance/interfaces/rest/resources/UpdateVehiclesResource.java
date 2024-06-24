package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record UpdateVehiclesResource(String plate, String make, String model, Long year, String color, String image_Url, Long mileage) {
}
