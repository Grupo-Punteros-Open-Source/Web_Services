package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InvoiceRepository  extends JpaRepository<Invoice,Long> {

}
