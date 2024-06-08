package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateWorkshopCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateWorkshopResource;
import org.springframework.stereotype.Component;

@Component
public class CreateWorkshopCommandFromResourceAssembler {
    public CreateWorkshopCommand toCommandFromResource(CreateWorkshopResource resource){
        return new CreateWorkshopCommand(
                resource.userId()
        );
    }
}
