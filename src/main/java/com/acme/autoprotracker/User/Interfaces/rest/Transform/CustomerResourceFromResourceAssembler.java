package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CustomerResources;

public class CustomerResourceFromResourceAssembler {
    public static CustomerResources toResourceFromEntity(Customer entity) {
        return new CustomerResources(
                entity.getId(),
                entity.getUser(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getImageUrl()
        );
    }
}
