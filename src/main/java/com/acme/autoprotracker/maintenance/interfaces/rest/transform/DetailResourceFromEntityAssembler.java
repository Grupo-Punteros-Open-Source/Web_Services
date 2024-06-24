package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.DetailResource;

public class DetailResourceFromEntityAssembler {
    public static DetailResource toResourceFromEntity(Detail entity) {
        return new DetailResource(entity.getId(), entity.getDescription(), entity.getAmount(), entity.getMaintenanceId().getId());
    }
}
