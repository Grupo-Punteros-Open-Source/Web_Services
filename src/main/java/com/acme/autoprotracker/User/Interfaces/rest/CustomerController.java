package com.acme.autoprotracker.User.Interfaces.rest;

import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllCustomerQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetCustomerByIdQuery;
import com.acme.autoprotracker.User.Domain.Services.CustomerCommandService;
import com.acme.autoprotracker.User.Domain.Services.CustomerQueryService;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.*;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = "application/json")
@Tag(name = "Customers", description = "Customer Management Endpoints")
public class CustomerController {

    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;

    public CustomerController(CustomerCommandService customerCommandService, CustomerQueryService customerQueryService) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
    }

    @PostMapping
    public ResponseEntity<CustomerResource> createCustomer(@RequestBody CreateCustomerResource createCustomerResource) {
        var createCustomerCommand = CreateCustomerCommandFromResourceAssembler.toCommandFromResource(createCustomerResource);
        var customerId = customerCommandService.handle(createCustomerCommand);
        if (customerId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getCustomerByIdQuery = new GetCustomerByIdQuery(customerId);
        var customer = customerQueryService.handle(getCustomerByIdQuery);
        if (customer.isEmpty()) return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(customer.get());
        return new ResponseEntity<>(customerResource, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable Long customerId) {
        var getCustomerByIdQuery = new GetCustomerByIdQuery(customerId);
        var customer = customerQueryService.handle(getCustomerByIdQuery);
        if (customer.isEmpty()) return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(customer.get());
        return ResponseEntity.ok(customerResource);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResource>> getAllCustomers() {
        var getAllCustomersQuery = new GetAllCustomerQuery();
        var customers = customerQueryService.handle(getAllCustomersQuery);
        var customerResources = customers.stream().map(CustomerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(customerResources);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerResource updateCustomerResource) {
        var updateCustomerCommand = UpdateCustomerCommandFromResourceAssembler.toCommandFromResource(updateCustomerResource, customerId);
        var updatedCustomer = customerCommandService.handle(updateCustomerCommand);
        if (updatedCustomer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(updatedCustomer.get());
        return ResponseEntity.ok(customerResource);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        var deleteCustomerCommand = new DeleteCustomerCommand(customerId);
        customerCommandService.handle(deleteCustomerCommand);
        return ResponseEntity.ok("Customer with given id successfully deleted");
    }
}
