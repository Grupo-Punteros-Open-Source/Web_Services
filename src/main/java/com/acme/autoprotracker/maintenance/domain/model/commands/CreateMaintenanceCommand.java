package com.acme.autoprotracker.maintenance.domain.model.commands;

public record CreateMaintenanceCommand(String status, String lastVisitDate, String comment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
