package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateNotificationCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateNotificationResource;

public class UpdateNotificationCommandFromResourceAssembler {

    public static UpdateNotificationCommand toCommandFromResource(UpdateNotificationResource resource, Long id) {
        return new UpdateNotificationCommand(
                id,
                resource.type(),
                resource.title(),
                resource.message(),
                resource.timestamp(),
                resource.read()
        );
    }
}