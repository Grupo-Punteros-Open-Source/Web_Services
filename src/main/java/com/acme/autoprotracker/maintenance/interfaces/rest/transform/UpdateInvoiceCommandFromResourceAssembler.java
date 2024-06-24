package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateInvoiceCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateDetailResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateInvoiceResource;

public class UpdateInvoiceCommandFromResourceAssembler {
    public static UpdateInvoiceCommand toCommandFromResource(Long id, UpdateInvoiceResource resource) {
        return new UpdateInvoiceCommand(id, resource.number(), resource.issue_date(),resource.total(),resource.status(),resource.detail());
    }
}
