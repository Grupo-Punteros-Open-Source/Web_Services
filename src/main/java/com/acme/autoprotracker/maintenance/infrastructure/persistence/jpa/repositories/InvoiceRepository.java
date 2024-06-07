package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository  extends JpaRepository<Invoice,Long> {
    Optional<Invoice> findByInvoiceCode(InvoiceCode invoiceCode);
    boolean existsByInvoiceCode(InvoiceCode invoiceCode);

}
