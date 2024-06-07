package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;
import com.acme.autoprotracker.maintenance.domain.services.InvoiceCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.DetailRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceCommandServicelmpl implements InvoiceCommandService {
    private final InvoiceRepository invoiceRepository;
    public InvoiceCommandServicelmpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Long handle(CreateInvoiceCommand command) {
        if (invoiceRepository.existsByInvoiceCode(command.invoiceCode())) {
            throw new IllegalArgumentException("Details with same maintanceId already exists");
        }
        var invoice = new Invoice(command);
        try {
            invoiceRepository.save(invoice);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return invoice.getId();
    }

    @Override
    public Optional<Invoice> handle(UpdateInvoiceCommand command) {
        var result = invoiceRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Invoice does not exist");
        var invoiceToUpdate = result.get();
        try {
            var invoiceUpdated = invoiceRepository.save(invoiceToUpdate.updateInvoice(command.invoiceCode(),command.issueDate(),command.total(),command.status(),command.detail()));
            return Optional.of(invoiceUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating invoice: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteInvoiceCommand command) {
        if (!invoiceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Invoice does not exist");
        }
        try {
            invoiceRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting detail: " + e.getMessage());
        }

    }


}
