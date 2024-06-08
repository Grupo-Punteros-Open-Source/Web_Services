package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintance;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;

import java.util.Optional;

public interface MaintanceCommandService {
    Long handle(CreateMaintanceCommand command);
    Optional<Maintance> handle(UpdateMaintanceCommand command);
    void handle(DeleteMaintanceCommand command);
}
