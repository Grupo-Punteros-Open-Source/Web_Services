package com.acme.autoprotracker.workshop.interfaces.rest.transform;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(entity.getId(), entity.getName(), entity.getQuantity(), entity.getPrice(), entity.getProductImage(), entity.getWorkshopId());
    }
}
