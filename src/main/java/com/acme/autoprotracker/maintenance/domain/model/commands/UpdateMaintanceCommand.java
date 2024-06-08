package com.acme.autoprotracker.maintenance.domain.model.commands;

import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceStatus;

import java.math.BigDecimal;
import java.util.Date;

public record UpdateMaintanceCommand(Long id, String status, Date lastvisitdate, String coment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
}
