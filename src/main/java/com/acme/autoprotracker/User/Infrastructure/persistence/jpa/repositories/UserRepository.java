package com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}