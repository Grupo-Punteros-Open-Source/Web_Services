package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateMaintanceCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateVehicleCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateVehiclesResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdatedMaintanceResource;

public class UpdateMaintanceCommandFromResourceAssembler {
    public static UpdateMaintanceCommand toCommandFromResource(Long id, UpdatedMaintanceResource resource) {
        return new UpdateMaintanceCommand(id, resource.status(),resource.lastvisitdate(),resource.coment(),
                resource.invoiceId(), resource.customerId(), resource.workshopId(), resource.vehicleId(), resource.historyId());
    }
}
