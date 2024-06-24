package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Domain.Model.Commands.*;
import com.acme.autoprotracker.User.Domain.Services.CustomerCommandService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class  CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;
    /* private final UserRepository userRepository;*/

    @Autowired
    public CustomerCommandServiceImpl(CustomerRepository customerRepository/*, UserRepository userRepository*/) {
        this.customerRepository = customerRepository;
        /*this.userRepository = userRepository;*/
    }

    @Override
    public Long handle(CreateCustomerCommand command) {
        /*var userResult = userRepository.findById(command.userId());
        var user = userResult.get();*/
        /*1L por user*/
        Customer customer = new Customer(command, 1L);
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

        /*var userResult = userRepository.findById(command.userId());
        if (userResult.isEmpty()) throw new UserNotFoundException(command.userId());
        var user = userResult.get();*/

        try {
            var updatedCustomer = customerRepository.save(customerToUpdate.update(
                    1L,
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
