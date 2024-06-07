package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(resource.name(), resource.quantity(),resource.price(),resource.productImage(),resource.workshopId());
    }
}
