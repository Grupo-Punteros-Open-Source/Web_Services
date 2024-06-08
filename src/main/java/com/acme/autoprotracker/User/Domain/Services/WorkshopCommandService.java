package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteWorkshopCommand;

public interface WorkshopCommandService {
    Long handle(CreateWorkshopCommand command);

    void handle(DeleteWorkshopCommand command);
}
