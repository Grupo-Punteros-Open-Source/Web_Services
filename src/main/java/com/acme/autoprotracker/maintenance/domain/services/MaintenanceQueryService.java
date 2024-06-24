package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllMaintenanceQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintenanceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintenanceByVehicleIdQuery;

import java.util.List;
import java.util.Optional;

public interface MaintenanceQueryService {
    Optional<Maintenance> handle(GetMaintenanceByIdQuery query);
    List<Maintenance> handle(GetAllMaintenanceQuery query);
    Optional<Maintenance> handle(GetMaintenanceByVehicleIdQuery query);
}
