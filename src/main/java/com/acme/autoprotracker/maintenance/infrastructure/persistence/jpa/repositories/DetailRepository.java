package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {

}
