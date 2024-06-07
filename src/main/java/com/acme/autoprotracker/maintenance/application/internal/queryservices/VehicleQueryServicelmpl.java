package com.acme.autoprotracker.maintenance.application.internal.queryservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Vehicle;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllVehiclesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetVehicleByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.VehicleQueryService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServicelmpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryServicelmpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.id());
    }
    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }


}
