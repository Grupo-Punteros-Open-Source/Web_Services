package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.User.Domain.Exceptions.UserNotFoundException;
import com.acme.autoprotracker.maintenance.domain.model.commands.CreateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.services.DetailCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.DetailRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailCommandServicelmpl implements DetailCommandService {
    private final DetailRepository detailRepository;
    private final MaintenanceRepository maintenanceRepository;
    public DetailCommandServicelmpl(DetailRepository detailRepository, MaintenanceRepository maintanceRepository) {
        this.detailRepository = detailRepository;
        this.maintenanceRepository = maintanceRepository;
    }

    @Override
    public Long handle(CreateDetailCommand command) {
        if (!maintenanceRepository.existsById(command.maintenance_id())) {
            throw new IllegalArgumentException("Maintenance with id " + command.maintenance_id() + " does not exist");
        }

        var maintenanceResult = maintenanceRepository.findById(command.maintenance_id());
        var maintenance = maintenanceResult.get();

        var detail = new Detail(command, maintenance);
        try {
            detailRepository.save(detail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving detail: " + e.getMessage());
        }
        return detail.getId();
    }

    @Override
    public Optional<Detail> handle(UpdateDetailCommand command) {
        var result = detailRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Detail does not exist");
        var detailToUpdate = result.get();

        var maintenanceResult = maintenanceRepository.findById(command.id());
        if (maintenanceResult.isEmpty()) throw new IllegalArgumentException("Maintenance does not exist");
        var maintenance = maintenanceResult.get();

        try {
            var detailUpdated = detailRepository.save(detailToUpdate.updateDetails(command.description(), command.amount(),maintenance));
            return Optional.of(detailUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating detail: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteDetailCommand command) {
        if (!detailRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Course does not exist");
        }
        try {
            detailRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting detail: " + e.getMessage());
        }

    }
}
