package com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.User.Domain.Model.Entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
