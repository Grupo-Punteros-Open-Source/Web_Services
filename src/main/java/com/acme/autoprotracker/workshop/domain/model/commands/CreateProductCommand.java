package com.acme.autoprotracker.workshop.domain.model.commands;

import java.math.BigDecimal;

public record CreateProductCommand(String name, Long quantity, BigDecimal price, String productImage, Long workshopId) {
}
