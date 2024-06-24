package com.acme.autoprotracker.workshop.interfaces.rest.resources;

import java.math.BigDecimal;

public record ProductResource(Long id, String name, Long quantity, BigDecimal price, String image_url, Long workshop_id) {
}
