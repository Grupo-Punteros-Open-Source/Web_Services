package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintance;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.InvoiceResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.MaintanceResource;

public class MaintanceResourceFromEntityAssembler {
    public static MaintanceResource toResourceFromEntity(Maintance entity) {
        return new MaintanceResource(entity.getId(), entity.getStatus(),entity.getLastvisitdate(),entity.getComent(),
                entity.getInvoiceId(), entity.getCustomerId(), entity.getWorkshopId(), entity.getVehicleId(), entity.getHistoryId());
    }
}
