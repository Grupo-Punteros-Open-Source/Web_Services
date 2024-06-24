package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateWorkshopCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateWorkshopResource;

public class CreateWorkshopCommandFromResourceAssembler {public static CreateWorkshopCommand toCommandFromResource(CreateWorkshopResource resource) {
    return new CreateWorkshopCommand(
            resource.userId(),
            resource.name(),
            resource.address(),
            resource.phone(),
            resource.email(),
            resource.image_url()
    );
}
}
