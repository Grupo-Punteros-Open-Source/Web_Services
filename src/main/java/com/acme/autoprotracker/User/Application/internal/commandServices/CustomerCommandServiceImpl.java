package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Domain.Services.CustomerCommandService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerCommandServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long handle(CreateCustomerCommand command) {
        Customer customer = new Customer();
        customer.setUserId(command.userId());
        // set other customer properties
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public void handle(DeleteCustomerCommand command) {
        if (!customerRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Course does not exist");
        }
        try {
            customerRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting course: " + e.getMessage());
        }
    }






}