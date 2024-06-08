package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.commands.CreateAdvertisingCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.CreateAdvertisingResource;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.CreateProductResource;

public class CreateAdvertisingCommandFromResourceAssembler {
    public static CreateAdvertisingCommand toCommandFromResource(CreateAdvertisingResource resource) {
        return new CreateAdvertisingCommand(resource.name(), resource.image_url(),resource.slogan(), resource.message(), resource.workshopId());
    }
}
