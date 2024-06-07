package com.acme.autoprotracker.maintenance.application.internal.queryservices;


import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllInvoicesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetInvoiceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.InvoiceQueryService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.DetailRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceQueryServicelmpl implements InvoiceQueryService {
    private final InvoiceRepository invoiceRepository;
    public InvoiceQueryServicelmpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Optional<Invoice> handle(GetInvoiceByIdQuery query) {
        return invoiceRepository.findById(query.id());
    }
    @Override
    public List<Invoice> handle(GetAllInvoicesQuery query) {
        return invoiceRepository.findAll();
    }

}
