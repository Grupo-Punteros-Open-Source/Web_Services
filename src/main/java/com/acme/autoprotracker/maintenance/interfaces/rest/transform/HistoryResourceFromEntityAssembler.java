package com.acme.autoprotracker.maintenance.interfaces.rest.transform;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.History;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.HistoryResource;

public class HistoryResourceFromEntityAssembler {
    public static HistoryResource toResourceFromEntity(History entity) {
        return new HistoryResource(entity.getId(), entity.getService_date(), entity.getDescription(), entity.getCost(), entity.getMileage());
    }
}
