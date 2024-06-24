package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.MaintenanceResource;

public class MaintanceResourceFromEntityAssembler {
    public static MaintenanceResource toResourceFromEntity(Maintenance entity) {
        return new MaintenanceResource(entity.getId(), entity.getStatus(),entity.getLastvisitdate(),entity.getComent(),
                entity.getInvoiceId(), entity.getCustomerId(), entity.getWorkshopId(), entity.getVehicleId(), entity.getHistoryId());
    }
}
