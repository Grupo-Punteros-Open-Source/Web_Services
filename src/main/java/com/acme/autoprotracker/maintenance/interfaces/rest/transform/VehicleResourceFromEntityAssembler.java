package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.InvoiceResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(entity.getId(), entity.getPlate(), entity.getMake(),
                entity.getModel(),entity.getYear(),entity.getColor(),entity.getImage_Url(),entity.getMileages());
    }
}
