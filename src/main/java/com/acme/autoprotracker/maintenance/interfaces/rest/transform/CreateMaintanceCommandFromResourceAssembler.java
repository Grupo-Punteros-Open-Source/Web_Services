package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateMaintanceCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateMaintanceResource;

public class CreateMaintanceCommandFromResourceAssembler {
    public static CreateMaintanceCommand toCommandFromResource(CreateMaintanceResource resource) {
        return new CreateMaintanceCommand(resource.status(),resource.lastvisitdate(),resource.coment(),
                resource.invoiceId(), resource.customerId(), resource.workshopId(), resource.vehicleId(), resource.historyId());
    }
}
