package com.acme.autoprotracker.maintenance.application.internal.queryservices;

import com.acme.autoprotracker.maintenance.domain.model.aggregates.Detail;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.DetailQueryService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DetailQueryServicelmpl implements DetailQueryService {
    private final DetailRepository detailRepository;
    public DetailQueryServicelmpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Optional<Detail> handle(GetDetailByIdQuery query) {
        return detailRepository.findById(query.id());
    }
    @Override
    public List<Detail> handle(GetAllDetailsQuery query) {
        return detailRepository.findAll();
    }


}
