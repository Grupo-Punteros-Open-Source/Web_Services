package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllInvoicesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetInvoiceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface InvoiceQueryService {
    Optional<Invoice> handle(GetInvoiceByIdQuery query);
    List<Invoice> handle(GetAllInvoicesQuery query);
}
