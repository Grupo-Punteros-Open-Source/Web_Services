package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateNotificationCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateCustomerResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateNotificationResource;

public class UpdateCustomerCommandFromResourceAssembler {
    public static UpdateCustomerCommand toCommandFromResource(UpdateCustomerResource resource, Long id) {
        return new UpdateCustomerCommand(
                id,
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email(),
                resource.imageUrl()
        );
    }
}
