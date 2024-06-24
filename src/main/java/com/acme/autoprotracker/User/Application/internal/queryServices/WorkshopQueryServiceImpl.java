package com.acme.autoprotracker.User.Application.internal.queryServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllWorkshopQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetWorkshopByIdQuery;
import com.acme.autoprotracker.User.Domain.Services.WorkshopQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopQueryServiceImpl implements WorkshopQueryService {

    private final WorkshopRepository workshopRepository;

    public WorkshopQueryServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    public Optional<Workshop> handle(GetWorkshopByIdQuery query) {
        return workshopRepository.findById(query.workshopId());
    }

    @Override
    public List<Workshop> handle(GetAllWorkshopQuery query) {
        return workshopRepository.findAll();
    }
}
