package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateCustomerCommand;

import java.util.Optional;

public interface CustomerCommandService {
    Long handle(CreateCustomerCommand command);
    Optional<Customer> handle(UpdateCustomerCommand command);
    void handle(DeleteCustomerCommand command);
}