package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findById(Long id);
}