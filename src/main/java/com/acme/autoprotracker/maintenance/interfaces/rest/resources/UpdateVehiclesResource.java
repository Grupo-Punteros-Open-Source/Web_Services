package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

import com.acme.autoprotracker.maintenance.domain.model.valueobjects.Plate;

public record UpdateVehiclesResource(String plate, String make, String model, Long year, String color, String image_Url, Long mileages) {
}
