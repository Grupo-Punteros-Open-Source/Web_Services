package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;

import java.util.Optional;

public interface VehicleCommandService {

    Long handle(CreateVehicleCommand command);
    Optional<Vehicle> handle(UpdateVehicleCommand command);
    void handle(DeleteVehicleCommand command);
}
