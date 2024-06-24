package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateHistoryCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateHistoryResource;

public class CreateHistoryCommandFromResourceAssembler {
    public static CreateHistoryCommand toCommandFromResource(CreateHistoryResource resource) {
        return new CreateHistoryCommand(resource.service_date(), resource.description(), resource.cost(), resource.mileage());
    }
}
