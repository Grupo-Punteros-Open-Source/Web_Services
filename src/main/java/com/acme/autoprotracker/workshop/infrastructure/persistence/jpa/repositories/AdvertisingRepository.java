package com.acme.autoprotracker.workshop.infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Advertising;
import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertisingRepository extends JpaRepository<Advertising,Long>  {
    Optional<Advertising> findByName(String name);
    boolean existsByName(String name);

}
