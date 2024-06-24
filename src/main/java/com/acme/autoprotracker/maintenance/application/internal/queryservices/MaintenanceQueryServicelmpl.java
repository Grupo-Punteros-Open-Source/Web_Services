package com.acme.autoprotracker.maintenance.application.internal.queryservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllMaintenanceQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintenanceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintenanceByVehicleIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.MaintenanceQueryService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceQueryServicelmpl implements MaintenanceQueryService {
    private final MaintenanceRepository maintanceRepository;
    public MaintenanceQueryServicelmpl(MaintenanceRepository maintanceRepository) {
        this.maintanceRepository = maintanceRepository;
    }
    @Override
    public Optional<Maintenance> handle(GetMaintenanceByIdQuery query) {
        return maintanceRepository.findById(query.id());
    }
    @Override
    public List<Maintenance> handle(GetAllMaintenanceQuery query) {
        return maintanceRepository.findAll();
    }

    @Override
    public Optional<Maintenance> handle(GetMaintenanceByVehicleIdQuery query) {
        return maintanceRepository.findByVehicleId(query.vehicleId());
    }

}
