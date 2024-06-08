package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintance;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllMaintanceQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintanceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MaintanceQueryService {
    Optional<Maintance> handle(GetMaintanceByIdQuery query);
    List<Maintance> handle(GetAllMaintanceQuery query);
}
