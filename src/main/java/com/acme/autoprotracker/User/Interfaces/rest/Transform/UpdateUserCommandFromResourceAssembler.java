package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateUserCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateUserResource;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandFromResourceAssembler {

    public UpdateUserCommand toCommandFromResource(UpdateUserResource resource, long id) {
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