package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.commands.CreateAdvertisingCommand;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.CreateAdvertisingResource;

public class CreateAdvertisingCommandFromResourceAssembler {
    public static CreateAdvertisingCommand toCommandFromResource(CreateAdvertisingResource resource) {
        return new CreateAdvertisingCommand(resource.comName(), resource.comImage(), resource.workshop_id(), resource.imageUrl(), resource.slogan(), resource.priceMsg(), resource.disMsg(), resource.repairMsg());
    }
}
