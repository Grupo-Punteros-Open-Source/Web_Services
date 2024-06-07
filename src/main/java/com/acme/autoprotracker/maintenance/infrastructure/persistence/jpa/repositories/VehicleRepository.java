package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository  extends JpaRepository<Vehicle,Long>  {
    Optional<Vehicle> findByPlate(Plate plate);
    boolean existsByPlate(Plate plate);
}
