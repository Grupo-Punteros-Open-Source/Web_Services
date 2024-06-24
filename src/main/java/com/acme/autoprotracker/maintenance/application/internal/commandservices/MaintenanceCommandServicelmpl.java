package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;
import com.acme.autoprotracker.maintenance.domain.services.MaintenanceCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MaintenanceCommandServicelmpl implements MaintenanceCommandService {

    public MaintenanceCommandServicelmpl(MaintenanceRepository maintanceRepository) {
        this.maintanceRepository = maintanceRepository;
    }

    private final MaintenanceRepository maintanceRepository;

    @Override
    public Long handle(CreateMaintenanceCommand command) {
        if (maintanceRepository.existsByVehicleId(command.vehicleId())) {
            throw new IllegalArgumentException("Maintance with same vehicleId already exists");
        }
        var maintance = new Maintenance(command);
        try {
            maintanceRepository.save(maintance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving maintance: " + e.getMessage());
        }
        return maintance.getId();
    }

    @Override
    public Optional<Maintenance> handle(UpdateMaintenanceCommand command) {
        var result = maintanceRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Maintance does not exist");
        var maintanceToUpdate = result.get();
        try {
            var maintanceUpdated = maintanceRepository.save(maintanceToUpdate.updateMaintance(command.status(),
                    command.lastvisitdate(),command.coment(), command.invoiceId(), command.customerId(), command.workshopId(), command.vehicleId(), command.historyId()));
            return Optional.of(maintanceUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating maintance: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMaintenanceCommand command) {
        if (!maintanceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Maintance does not exist");
        }
        try {
            maintanceRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting maintance " + e.getMessage());
        }

    }


}
