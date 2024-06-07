package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.DetailResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.InvoiceResource;

public class InvoiceResourceFromEntityAssembler {
    public static InvoiceResource toResourceFromEntity(Invoice entity) {
        return new InvoiceResource(entity.getId(), entity.getInvoiceCode(), entity.getIssueDate(),
                entity.getTotal(),entity.getStatus(),entity.getDetail());
    }
}
