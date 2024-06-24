package com.acme.autoprotracker.User.Application.internal.queryServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.Notification;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllCustomerQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllNotificationsQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetCustomerByIdQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetNotificationByIdQuery;
import com.acme.autoprotracker.User.Domain.Services.CustomerQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> handle(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.customerId());
    }

    @Override
    public List<Customer> handle(GetAllCustomerQuery query) {
        return customerRepository.findAll();
    }
}
