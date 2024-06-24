package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateVehicleCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                resource.plate(),
                resource.make(),
                resource.model(),
                resource.year(),
                resource.color(),
                resource.image_url(),
                resource.mileages(),
                resource.customer_id()
        );
    }
}