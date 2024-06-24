package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record CreateHistoryResource(String service_date, String description, Double cost, Long mileage) {
}
