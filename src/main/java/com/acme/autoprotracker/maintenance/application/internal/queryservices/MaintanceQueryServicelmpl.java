package com.acme.autoprotracker.maintenance.application.internal.queryservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintance;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllMaintanceQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintanceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.MaintanceQueryService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.MaintanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintanceQueryServicelmpl implements MaintanceQueryService {
    private final MaintanceRepository maintanceRepository;
    public MaintanceQueryServicelmpl(MaintanceRepository maintanceRepository) {
        this.maintanceRepository = maintanceRepository;
    }
    @Override
    public Optional<Maintance> handle(GetMaintanceByIdQuery query) {
        return maintanceRepository.findById(query.id());
    }
    @Override
    public List<Maintance> handle(GetAllMaintanceQuery query) {
        return maintanceRepository.findAll();
    }


}
