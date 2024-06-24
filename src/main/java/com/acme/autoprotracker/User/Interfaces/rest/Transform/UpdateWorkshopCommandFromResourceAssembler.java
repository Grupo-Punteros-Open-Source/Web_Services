package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateWorkshopCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateWorkshopResource;

public class UpdateWorkshopCommandFromResourceAssembler {
    public static UpdateWorkshopCommand toCommandFromResource(UpdateWorkshopResource resource, Long id) {
        return new UpdateWorkshopCommand(
                id,
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email(),
                resource.image_url()
        );
    }
}
