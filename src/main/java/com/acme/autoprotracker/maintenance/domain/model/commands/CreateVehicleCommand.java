package com.acme.autoprotracker.maintenance.domain.model.commands;

import com.acme.autoprotracker.maintenance.domain.model.valueobjects.Plate;

public record CreateVehicleCommand(Plate plate, String make, String model, Long year, String color, String image_Url, Long mileages) {
}
