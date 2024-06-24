package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.MaintenanceResource;

public class MaintenanceResourceFromEntityAssembler {
    public static MaintenanceResource toResourceFromEntity(Maintenance entity) {
        return new MaintenanceResource(entity.getId(), entity.getStatus(),entity.getLastVisitDate(),entity.getComment(),
                entity.getInvoiceId().getId(), entity.getCustomerId().getId(), entity.getWorkshopId().getId(), entity.getVehicleId().getId(), entity.getHistoryId().getId());
    }
}
