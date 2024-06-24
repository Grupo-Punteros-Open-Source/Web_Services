package com.acme.autoprotracker.workshop.domain.services;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Advertising;
import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AdvertisingQueryService {
    Optional<Advertising> handle(GetAdvertisingByIdQuery query);
    List<Advertising> handle(GetAllAdvertisingQuery query);

}
