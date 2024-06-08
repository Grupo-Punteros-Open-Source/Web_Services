package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.commands.UpdateAdvertisingCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.UpdateProductCommand;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.UpdateAdvertisingResource;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.UpdateProductResource;

public class UpdateAdvertisingCommandFromResourceAssembler {
    public static UpdateAdvertisingCommand toCommandFromResource(Long id, UpdateAdvertisingResource resource) {
        return new UpdateAdvertisingCommand(id, resource.name(), resource.image_url(),resource.slogan(),resource.message(),resource.workshopId());
    }
}
