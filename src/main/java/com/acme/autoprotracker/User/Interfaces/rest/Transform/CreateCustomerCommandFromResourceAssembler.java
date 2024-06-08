package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateCustomerResource;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerCommandFromResourceAssembler {
    public CreateCustomerCommand toCommandFromResource(CreateCustomerResource resource){
        return new CreateCustomerCommand(
                resource.userId()
        );
    }
}
