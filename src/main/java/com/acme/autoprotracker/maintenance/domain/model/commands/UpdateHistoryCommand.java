package com.acme.autoprotracker.maintenance.domain.model.commands;

import java.util.Date;

public record UpdateHistoryCommand(Long id, String service, String description, Double cost, Long mileage) {
}
