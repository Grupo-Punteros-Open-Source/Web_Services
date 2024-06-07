package com.acme.autoprotracker.maintenance.domain.exceptions;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(Long aLong) {
        super("Invoice with id " + aLong + " not found");
    }
}
