package com.acme.autoprotracker.workshop.domain.model.commands;

import java.math.BigDecimal;

public record UpdateProductCommand(Long id , String name, Long quantity, BigDecimal price, String productImage, Long workshopId) {
}
