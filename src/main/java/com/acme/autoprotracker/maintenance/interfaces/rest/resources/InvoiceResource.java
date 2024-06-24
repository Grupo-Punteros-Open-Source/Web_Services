package com.acme.autoprotracker.maintenance.interfaces.rest.resources;


public record InvoiceResource(Long id, String number, String issue_date, Long total, String status, String detail) {
}
