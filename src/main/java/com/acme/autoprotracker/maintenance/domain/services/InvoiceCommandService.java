package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;

import java.util.Optional;

public interface InvoiceCommandService {
    Long handle(CreateInvoiceCommand command);
    Optional<Invoice> handle(UpdateInvoiceCommand command);
    void handle(DeleteInvoiceCommand command);
}
