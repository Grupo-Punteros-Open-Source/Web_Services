package com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
    Optional<Workshop> findByUser(User user);
}