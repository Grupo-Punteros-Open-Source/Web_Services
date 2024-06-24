package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

import java.util.Date;

public record UpdatedMaintenanceResource(String status, Date lastvisitdate, String coment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
