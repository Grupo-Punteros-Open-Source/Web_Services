package com.acme.autoprotracker.maintenance.domain.model.commands;

public record UpdateMaintenanceCommand(Long id, String status, String lastVisitDate, String comment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
