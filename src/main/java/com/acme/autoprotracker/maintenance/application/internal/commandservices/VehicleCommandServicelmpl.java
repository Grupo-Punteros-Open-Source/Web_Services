package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;
import com.acme.autoprotracker.maintenance.domain.services.VehicleCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleCommandServicelmpl implements VehicleCommandService {
    private final VehicleRepository vehicleRepository;

    public VehicleCommandServicelmpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Long handle(CreateVehicleCommand command) {
        if (vehicleRepository.existsByPlate(command.plate())) {
            throw new IllegalArgumentException("Vehicle with same plate already exists");
        }
        var vehicle = new Vehicle(command);
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return vehicle.getId();
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Vehicle does not exist");
        var vehicleToUpdate = result.get();
        try {
            var vehicleUpdated = vehicleRepository.save(vehicleToUpdate.updateVehicle(command.plate(),command.make(),command.model(),command.year(),command.color(),command.image_Url(), command.mileages()));
            return Optional.of(vehicleUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating invoice: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteVehicleCommand command) {
        if (!vehicleRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Vehicle does not exist");
        }
        try {
            vehicleRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting detail: " + e.getMessage());
        }

    }
}
