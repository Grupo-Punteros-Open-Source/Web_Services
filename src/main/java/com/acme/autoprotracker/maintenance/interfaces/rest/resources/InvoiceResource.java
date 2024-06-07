package com.acme.autoprotracker.maintenance.interfaces.rest.resources;

import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceStatus;

import java.math.BigDecimal;
import java.util.Date;

public record InvoiceResource(Long id, InvoiceCode invoiceCode, Date issueDate, BigDecimal total, InvoiceStatus status, String detail) {
}
