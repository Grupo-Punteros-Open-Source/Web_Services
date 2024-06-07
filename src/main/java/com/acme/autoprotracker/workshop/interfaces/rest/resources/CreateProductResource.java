package com.acme.autoprotracker.workshop.interfaces.rest.resources;

import java.math.BigDecimal;

public record CreateProductResource(String name, Long quantity, BigDecimal price, String productImage, Long workshopId) {
}
