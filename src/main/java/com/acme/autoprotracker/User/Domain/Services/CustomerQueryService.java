package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CustomerQueryService {
    public boolean userExists(Long userId);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getAllCustomer();
}
