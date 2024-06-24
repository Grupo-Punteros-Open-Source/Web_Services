package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;

import java.util.Optional;

public interface MaintenanceCommandService {
    Long handle(CreateMaintenanceCommand command);
    Optional<Maintenance> handle(UpdateMaintenanceCommand command);
    void handle(DeleteMaintenanceCommand command);
}
