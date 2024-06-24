package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record UpdateHistoryResource(String service_date, String description, Double cost, Long mileage) {
}
