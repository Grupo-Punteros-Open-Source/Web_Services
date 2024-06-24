package com.acme.autoprotracker.maintenance.domain.model.commands;

public record UpdateInvoiceCommand(Long id, String number, String issue_date, Long total, String status, String detail) {
}
