package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import org.springframework.stereotype.Service;


public interface CustomerCommandService {
    Long handle(CreateCustomerCommand command);

    void handle(DeleteCustomerCommand command);
}
