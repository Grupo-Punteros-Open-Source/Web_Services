package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateWorkshopCommand;

import java.util.Optional;

public interface WorkshopCommandService {
    Long handle(CreateWorkshopCommand command);
    Optional<Workshop> handle(UpdateWorkshopCommand command);
    void handle(DeleteWorkshopCommand command);
}
