package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.commands.UpdateProductCommand;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(Long id, UpdateProductResource resource) {
        return new UpdateProductCommand(id, resource.name(), resource.quantity(),resource.price(),resource.productImage(),resource.workshopId());
    }
}
