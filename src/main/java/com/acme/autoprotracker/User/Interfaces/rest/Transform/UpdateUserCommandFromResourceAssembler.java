package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateUserCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateUserResource;



public class UpdateUserCommandFromResourceAssembler {

    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource, Long id) {
        return new UpdateUserCommand(
                id,
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email(),
                resource.imageUrl(),
                resource.username(),
                resource.password()
        );
    }
}