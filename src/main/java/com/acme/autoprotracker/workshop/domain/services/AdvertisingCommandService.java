package com.acme.autoprotracker.workshop.domain.services;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Advertising;
import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.domain.model.commands.*;

import java.util.Optional;

public interface AdvertisingCommandService {
    Long handle(CreateAdvertisingCommand command);
    Optional<Advertising> handle(UpdateAdvertisingCommand command);
    void handle(DeleteAdvertisingCommand command);
}
