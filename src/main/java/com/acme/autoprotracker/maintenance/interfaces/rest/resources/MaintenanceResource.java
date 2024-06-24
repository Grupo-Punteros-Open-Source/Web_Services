package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

import java.util.Date;

public record MaintenanceResource(Long id, String status, String lastVisitDate, String comment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
