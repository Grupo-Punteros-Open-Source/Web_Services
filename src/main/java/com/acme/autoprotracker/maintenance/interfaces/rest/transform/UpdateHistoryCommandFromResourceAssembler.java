package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateHistoryCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateHistoryResource;

public class UpdateHistoryCommandFromResourceAssembler {
    public static UpdateHistoryCommand toCommandFromResource(Long id, UpdateHistoryResource resource) {
        return new UpdateHistoryCommand(id, resource.service(), resource.description(), resource.cost(), resource.mileage());
    }
}
