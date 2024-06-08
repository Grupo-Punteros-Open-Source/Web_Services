package com.acme.autoprotracker.maintenance.application.internal.queryservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.History;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllHistoriesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetHistoryByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.HistoryQueryService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.HistoryRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HistoryQueryServiceImpl implements HistoryQueryService {
    private final HistoryRepository historyRepository;
    public HistoryQueryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    @Override
    public Optional<History> handle(GetHistoryByIdQuery query) {
        return historyRepository.findById(query.id());
    }
    @Override
    public List<History> handle(GetAllHistoriesQuery query) {
        return historyRepository.findAll();
    }

}
