package com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailRepository extends JpaRepository <Detail,Long>{
    Optional<Detail> findByMaintanceId(Long id);
    boolean existsByMaintanceId(Long id);

}
