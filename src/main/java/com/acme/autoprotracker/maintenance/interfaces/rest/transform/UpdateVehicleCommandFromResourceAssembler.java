package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.commands.UpdateVehicleCommand;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateVehiclesResource;

public class UpdateVehicleCommandFromResourceAssembler {
    public static UpdateVehicleCommand toCommandFromResource(Long id, UpdateVehiclesResource resource) {
        return new UpdateVehicleCommand(id, resource.plate(), resource.make(),resource.model(),resource.year(),resource.color(),resource.image_Url(),resource.mileage());
    }
}
