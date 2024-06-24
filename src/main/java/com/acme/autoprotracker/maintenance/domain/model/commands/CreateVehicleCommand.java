package com.acme.autoprotracker.maintenance.domain.model.commands;

public record CreateVehicleCommand(String plate, String make, String model, Long year, String color, String image_Url, Long mileages, Long customerId) {
}