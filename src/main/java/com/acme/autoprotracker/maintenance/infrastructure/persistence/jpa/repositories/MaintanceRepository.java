package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Invoice;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintance;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaintanceRepository extends JpaRepository<Maintance,Long> {
    Optional<Main> findByVehicleId(Long vehicleId);
    boolean existsByVehicleId(Long vehicleId);
}
