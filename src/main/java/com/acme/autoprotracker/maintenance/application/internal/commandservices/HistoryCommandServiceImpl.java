package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.History;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;
import com.acme.autoprotracker.maintenance.domain.services.HistoryCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryCommandServiceImpl implements HistoryCommandService {

    private final HistoryRepository historyRepository;
    public HistoryCommandServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Long handle(CreateHistoryCommand command) {
        if (historyRepository.existsById(command.mileage())) {
            throw new IllegalArgumentException("History with same description already exists");
        }
        var history = new History(command);
        try {
            historyRepository.save(history);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving histories: " + e.getMessage());
        }
        return history.getId();
    }

    @Override
    public Optional<History> handle(UpdateHistoryCommand command) {
        if (historyRepository.existsByMileageAndIdIsNot(command.mileage(), command.id()))
            throw new IllegalArgumentException("History with same description already exists");
        var result = historyRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("History does not exist");
        var historyToUpdate = result.get();
        try {
            var historyUpdated = historyRepository.save(historyToUpdate.updateHistory(command.service(), command.description(), command.cost(), command.mileage()));
            return Optional.of(historyUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating history: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteHistoryCommand command) {
        if (!historyRepository.existsById(command.id())) {
            throw new IllegalArgumentException("History does not exist");
        }
        try {
            historyRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting history: " + e.getMessage());
        }

    }

}
