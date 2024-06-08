package com.acme.autoprotracker.maintenance.domain.model.commands;

import java.util.Date;

public record CreateHistoryCommand(String service, String description, Double cost, Long mileage) {
}
