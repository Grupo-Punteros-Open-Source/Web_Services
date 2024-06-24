package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record HistoryResource(Long id, String service_date, String description, Double cost, Long mileage) {
}
