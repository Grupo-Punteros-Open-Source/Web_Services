package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllCustomerQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetCustomerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CustomerQueryService {
    Optional<Customer> handle (GetCustomerByIdQuery query);
    List<Customer> handle (GetAllCustomerQuery query);
}
