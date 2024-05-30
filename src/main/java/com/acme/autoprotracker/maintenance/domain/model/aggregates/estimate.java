package com.acme.autoprotracker.maintenance.domain.model.aggregates;

import com.acme.autoprotracker.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class estimate extends AuditableAbstractAggregateRoot<estimate> {
    private String date;
    private String description;
    private String cost;

    @Embedded
    private
}
