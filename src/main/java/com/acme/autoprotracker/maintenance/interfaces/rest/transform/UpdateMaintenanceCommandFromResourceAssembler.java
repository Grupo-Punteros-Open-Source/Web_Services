package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateMaintenanceCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdatedMaintenanceResource;

public class UpdateMaintenanceCommandFromResourceAssembler {
    public static UpdateMaintenanceCommand toCommandFromResource(Long id, UpdatedMaintenanceResource resource) {
        return new UpdateMaintenanceCommand(id, resource.status(),resource.lastvisitdate(),resource.coment(),
                resource.invoiceId(), resource.customerId(), resource.workshopId(), resource.vehicleId(), resource.historyId());
    }
}
