package com.acme.autoprotracker.workshop.interfaces.rest.resources;

import java.math.BigDecimal;

public record UpdateProductResource(String name, Long quantity, BigDecimal price, String productImage, Long workshopId) {
}
