package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateCustomerResource;


public class CreateCustomerCommandFromResourceAssembler {  public static CreateCustomerCommand toCommandFromResource(CreateCustomerResource resource) {
    return new CreateCustomerCommand(
            resource.userId(),
            resource.name(),
            resource.address(),
            resource.phone(),
            resource.email(),
            resource.imageUrl()
    );
}
}