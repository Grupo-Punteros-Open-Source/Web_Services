package com.acme.autoprotracker.workshop.domain.services;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.DeleteProductCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {

    Long handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}
