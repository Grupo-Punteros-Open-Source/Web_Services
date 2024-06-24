package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

public record UpdatedMaintenanceResource(String status, String lastVisitDate, String comment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
