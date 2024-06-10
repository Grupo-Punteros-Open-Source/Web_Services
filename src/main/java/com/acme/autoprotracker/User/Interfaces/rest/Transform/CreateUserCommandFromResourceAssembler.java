package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateUserCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
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