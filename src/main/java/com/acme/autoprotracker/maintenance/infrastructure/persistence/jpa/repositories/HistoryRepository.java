package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HistoryRepository extends JpaRepository<History,Long> {
    boolean existsById(Long id);
    boolean existsByMileageAndIdIsNot(Long mileage, Long id);

}
