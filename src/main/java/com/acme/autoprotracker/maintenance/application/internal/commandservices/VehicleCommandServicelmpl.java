package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;
import com.acme.autoprotracker.maintenance.domain.services.VehicleCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.VehicleRepository;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class VehicleCommandServicelmpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public VehicleCommandServicelmpl(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Long handle(CreateVehicleCommand command) {
        var customerResult = customerRepository.findById(command.customerId());
        if (customerResult.isEmpty()) throw new IllegalArgumentException("Customer does not exist");
        var customer = customerResult.get();

        var vehicle = new Vehicle(command, customer);
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving vehicle: " + e.getMessage());
        }
        return vehicle.getId();
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Vehicle does not exist");
        var vehicleToUpdate = result.get();


        try {
            var updatedVehicle = vehicleRepository.save(vehicleToUpdate.updateVehicle(
                    command.plate(),
                    command.make(),
                    command.model(),
                    command.year(),
                    command.color(),
                    command.image_Url(),
                    command.mileages()
            ));
            return Optional.of(updatedVehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating vehicle: " + e.getMessage());
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
            throw new IllegalArgumentException("Error while deleting vehicle: " + e.getMessage());
        }
    }
}