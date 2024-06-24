package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateInvoiceCommand;

import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateInvoiceResource;

public class CreateInvoiceCommandFromResourceAssembler {
    public static CreateInvoiceCommand toCommandFromResource(CreateInvoiceResource resource) {
        return new CreateInvoiceCommand(resource.number(), resource.issue_date(),resource.total(),resource.status(),resource.detail());
    }
}
