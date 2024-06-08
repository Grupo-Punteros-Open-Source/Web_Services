package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

import java.util.Date;

public record CreateHistoryResource(String service, String description, Double cost, Long mileage) {
}
