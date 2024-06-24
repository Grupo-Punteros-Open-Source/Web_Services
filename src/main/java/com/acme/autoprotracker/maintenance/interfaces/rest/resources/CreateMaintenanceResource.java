package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record CreateMaintenanceResource(String status, String lastVisitDate, String comment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
