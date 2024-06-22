package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Advertising;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.AdvertisingResource;

public class AdvertisingResourceFromEntityAssembler {
    public static AdvertisingResource toResourceFromEntity(Advertising entity) {
        return new AdvertisingResource(entity.getId(), entity.getComName(), entity.getComImage(), entity.getWorkshopId(), entity.getImageUrl(), entity.getSlogan(), entity.getPriceMsg(), entity.getDisMsg(), entity.getRepairMsg());
    }
}
