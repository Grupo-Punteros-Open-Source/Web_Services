package com.acme.autoprotracker.workshop.domain.services;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.domain.model.queries.GetAllProductsQuery;
import com.acme.autoprotracker.workshop.domain.model.queries.GetProductByIdQuery;
import com.acme.autoprotracker.workshop.domain.model.queries.GetProductByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {

    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsQuery query);
    Optional<Product> handle(GetProductByNameQuery query);
}
