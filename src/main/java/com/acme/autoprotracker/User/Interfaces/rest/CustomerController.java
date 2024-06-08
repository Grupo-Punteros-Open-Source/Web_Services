package com.acme.autoprotracker.User.Interfaces.rest;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Domain.Services.CustomerCommandService;
import com.acme.autoprotracker.User.Domain.Services.CustomerQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateCustomerResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CustomerResource;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = "application/json")
public class CustomerController {
    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;
    private final CreateCustomerCommandFromResourceAssembler createCustomerCommandFromResourceAssembler;
    private final DeleteCustomerCommandFromResourceAssembler deleteCustomerCommandFromResourceAssembler;
    private final CustomerResourceFromEntityAssembler customerResourceFromEntityAssembler;

    private final CustomerRepository customerRepository;
    public CustomerController(CustomerCommandService customerCommandService, CustomerQueryService customerQueryService,
                              CreateCustomerCommandFromResourceAssembler createCustomerCommandFromResourceAssembler,
                              DeleteCustomerCommandFromResourceAssembler deleteCustomerCommandFromResourceAssembler,
                              CustomerResourceFromEntityAssembler customerResourceFromEntityAssembler,
                              CustomerRepository customerRepository, CustomerRepository customerRepository1) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
        this.createCustomerCommandFromResourceAssembler = createCustomerCommandFromResourceAssembler;
        this.deleteCustomerCommandFromResourceAssembler = deleteCustomerCommandFromResourceAssembler;
        this.customerResourceFromEntityAssembler = customerResourceFromEntityAssembler;
        this.customerRepository = customerRepository;
    }



    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerResource createCustomerResource) {
        if (!customerQueryService.userExists(createCustomerResource.userId())) {
            return ResponseEntity.badRequest().body("Invalid userId");
        }
        var createCustomerCommand = createCustomerCommandFromResourceAssembler.toCommandFromResource(createCustomerResource);
        var Id = customerCommandService.handle(createCustomerCommand);
        var customer = customerQueryService.getCustomerById(Id);
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resource = customerResourceFromEntityAssembler.toResourceFromEntity(customer.get());
        return ResponseEntity.created(null).body(resource);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long Id) {
        var deleteCustomerCommand = new DeleteCustomerCommand(Id);
        customerCommandService.handle(deleteCustomerCommand);
        return ResponseEntity.ok("Course with given id successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<CustomerResource>> getAllCustomers() {
        var customers = customerQueryService.getAllCustomer();
        var resources = customers.stream().map(customerResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable Long Id) {
        var customer = customerQueryService.getCustomerById(Id);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = customerResourceFromEntityAssembler.toResourceFromEntity(customer.get());
        return ResponseEntity.ok(resource);
    }

}
