package com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUser(User user);
}