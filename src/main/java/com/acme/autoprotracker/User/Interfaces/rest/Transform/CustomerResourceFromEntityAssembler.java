package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CustomerResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerResourceFromEntityAssembler {

    public CustomerResource toResourceFromEntity(Customer customer){
        return new CustomerResource(
                customer.getUserId(),
                customer.getId()
        );
    }

}
