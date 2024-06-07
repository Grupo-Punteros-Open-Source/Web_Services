package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateDetailCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateDetailResource;

public class CreateDetailCommandFromResourceAssembler {
    public static CreateDetailCommand toCommandFromResource(CreateDetailResource resource) {
        return new CreateDetailCommand(resource.description(), resource.amount(),resource.maintanceId());
    }
}
