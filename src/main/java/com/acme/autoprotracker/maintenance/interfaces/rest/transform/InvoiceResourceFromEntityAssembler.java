package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.InvoiceResource;

public class InvoiceResourceFromEntityAssembler {
    public static InvoiceResource toResourceFromEntity(Invoice entity) {
        return new InvoiceResource(entity.getId(), entity.getNumber(), entity.getIssue_date(),
                entity.getTotal(),entity.getStatus(),entity.getDetail());
    }
}
