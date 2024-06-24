package com.acme.autoprotracker.maintenance.domain.model.commands;

import java.util.Date;

public record UpdateMaintenanceCommand(Long id, String status, Date lastvisitdate, String coment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
