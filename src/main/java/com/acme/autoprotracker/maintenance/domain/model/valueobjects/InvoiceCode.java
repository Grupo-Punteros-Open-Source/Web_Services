package com.acme.autoprotracker.maintenance.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record InvoiceCode(String invoiceCode) {
    public InvoiceCode {
        if (!invoiceCode.matches("^[A-Z]\\d{7}$")) {
            throw new IllegalArgumentException("Invalid format for invoice number.");
        }
    }
}
