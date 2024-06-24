package com.acme.autoprotracker.maintenance.domain.model.commands;

public record UpdateVehicleCommand(Long id, String plate, String make, String model, Long year, String color, String image_Url, Long mileages) {
}