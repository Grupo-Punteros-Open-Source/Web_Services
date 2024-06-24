package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateMaintenanceCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateMaintenanceResource;

public class CreateMaintenanceCommandFromResourceAssembler {
    public static CreateMaintenanceCommand toCommandFromResource(CreateMaintenanceResource resource) {
        return new CreateMaintenanceCommand(resource.status(),resource.lastvisitdate(),resource.coment(),
                resource.invoiceId(), resource.customerId(), resource.workshopId(), resource.vehicleId(), resource.historyId());
    }
}
