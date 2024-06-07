package com.acme.autoprotracker.maintenance.domain.services;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;





public interface DetailQueryService {
    Optional<Detail> handle(GetDetailByIdQuery query);
    List<Detail> handle(GetAllDetailsQuery query);
}
