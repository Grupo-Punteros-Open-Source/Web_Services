package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.services.DetailCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailCommandServicelmpl implements DetailCommandService {
    private final DetailRepository detailRepository;
    public DetailCommandServicelmpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Long handle(CreateDetailCommand command) {
        if (detailRepository.existsByMaintanceId(command.maintanceId())) {
            throw new IllegalArgumentException("Details with same maintanceId already exists");
        }
        var detail = new Detail(command);
        try {
            detailRepository.save(detail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return detail.getId();
    }

    @Override
    public Optional<Detail> handle(UpdateDetailCommand command) {
        if (detailRepository.existsByMaintanceIdAndIdIsNot(command.maintanceId(), command.id()))
            throw new IllegalArgumentException("Detail with same maintanceId already exists");
        var result = detailRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Detail does not exist");
        var detailToUpdate = result.get();
        try {
            var detailUpdated = detailRepository.save(detailToUpdate.updateDetails(command.description(), command.amount(),command.maintanceId()));
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
