package com.acme.autoprotracker.User.Application.internal.queryServices;

import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Domain.Services.CustomerQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerQueryServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }



}