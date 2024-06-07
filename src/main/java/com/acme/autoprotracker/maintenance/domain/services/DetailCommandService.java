package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;


import java.util.Optional;


public interface DetailCommandService {
    Long handle(CreateDetailCommand command);
    Optional<Detail> handle(UpdateDetailCommand command);
    void handle(DeleteDetailCommand command);
}
