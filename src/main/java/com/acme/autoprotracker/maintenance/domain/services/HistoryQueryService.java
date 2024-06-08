package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.History;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllHistoriesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetHistoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface HistoryQueryService {
    Optional<History> handle(GetHistoryByIdQuery query);
    List<History> handle(GetAllHistoriesQuery query);

}
