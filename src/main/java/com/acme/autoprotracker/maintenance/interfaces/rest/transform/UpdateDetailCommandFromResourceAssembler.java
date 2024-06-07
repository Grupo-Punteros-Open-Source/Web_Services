package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateDetailCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateDetailResource;

public class UpdateDetailCommandFromResourceAssembler {
    public static UpdateDetailCommand toCommandFromResource(Long id, UpdateDetailResource resource) {
        return new UpdateDetailCommand(id, resource.description(), resource.amount(), resource.maintanceId());
    }
}
