package com.acme.autoprotracker.maintenance.domain.services;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.History;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;

import java.util.Optional;

public interface HistoryCommandService {
    Long handle(CreateHistoryCommand command);
    Optional<History> handle(UpdateHistoryCommand command);
    void handle(DeleteHistoryCommand command);
}
