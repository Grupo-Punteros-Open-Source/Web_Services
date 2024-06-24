package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
    Optional<Maintenance> findByVehicleId(Long vehicleId);
    boolean existsByVehicleId(Long vehicleId);
}
