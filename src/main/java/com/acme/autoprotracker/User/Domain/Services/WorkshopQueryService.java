package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllWorkshopQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetWorkshopByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WorkshopQueryService {
    Optional<Workshop> handle (GetWorkshopByIdQuery query);
    List<Workshop> handle (GetAllWorkshopQuery query);
}
