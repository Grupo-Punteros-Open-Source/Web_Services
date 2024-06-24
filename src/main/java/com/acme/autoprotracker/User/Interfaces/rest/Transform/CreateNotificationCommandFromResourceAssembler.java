package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateNotificationCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {

    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.userId(),
                resource.type(),
                resource.title(),
                resource.message(),
                resource.timestamp(),
                resource.read()
        );
    }
}