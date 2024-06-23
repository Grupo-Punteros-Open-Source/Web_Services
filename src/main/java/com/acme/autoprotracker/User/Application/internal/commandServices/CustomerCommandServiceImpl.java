package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Exceptions.UserNotFoundException;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Domain.Model.Commands.*;
import com.acme.autoprotracker.User.Domain.Services.CustomerCommandService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import com.acme.autoprotracker.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class  CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final WorkshopRepository workshopRepository;

    @Autowired
    public CustomerCommandServiceImpl(CustomerRepository customerRepository, UserRepository userRepository, WorkshopRepository workshopRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.workshopRepository = workshopRepository;
    }

    @Override
    public Long handle(CreateCustomerCommand command) {
        var userResult = userRepository.findById(command.userId());
        if (userResult.isEmpty()) throw new UserNotFoundException(command.userId());
        var user = userResult.get();

        var workshopResult = workshopRepository.findById(command.workshopId());
        var workshop = workshopResult.get();

        var existingCustomer = customerRepository.findByUser(user);
        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("A customer with the same userId already exists");
        }

        var existingWorkshop = workshopRepository.findByUser(user);
        if (existingWorkshop.isPresent()) {
            throw new IllegalArgumentException("A workshop with the same userId already exists");
        }

        Customer customer = new Customer(command, user, workshop);
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving customer: " + e.getMessage());
        }
        return customer.getId();
    }

    @Override
    public Optional<Customer> handle(UpdateCustomerCommand command) {
        var result = customerRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Customer does not exist");
        var customerToUpdate = result.get();

        var userResult = userRepository.findById(command.id());
        if (userResult.isEmpty()) throw new UserNotFoundException(command.id());
        var user = userResult.get();

        var workshopResult = workshopRepository.findById(command.id());
        if (workshopResult.isEmpty()) throw new IllegalArgumentException("Workshop does not exist");
        var workshop = workshopResult.get();

        try {
            var updatedCustomer = customerRepository.save(customerToUpdate.update(
                    user,
                    workshop,
                    command.name(),
                    command.address(),
                    command.phone(),
                    command.email(),
                    command.imageUrl()
            ));
            return Optional.of(updatedCustomer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating customer: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteCustomerCommand command) {
        if (!customerRepository.existsById(command.customerId())) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        try {
            customerRepository.deleteById(command.customerId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting customer: " + e.getMessage());
        }
    }
}
