package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Estimate, Long>{
}
