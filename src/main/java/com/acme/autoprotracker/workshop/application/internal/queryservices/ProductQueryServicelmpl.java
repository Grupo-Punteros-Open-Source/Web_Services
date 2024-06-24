package com.acme.autoprotracker.workshop.application.internal.queryservices;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.domain.model.queries.GetAllProductsQuery;
import com.acme.autoprotracker.workshop.domain.model.queries.GetProductByIdQuery;
import com.acme.autoprotracker.workshop.domain.model.queries.GetProductByNameQuery;
import com.acme.autoprotracker.workshop.domain.services.ProductQueryService;
import com.acme.autoprotracker.workshop.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServicelmpl implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServicelmpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id());
    }
    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> handle(GetProductByNameQuery query) {
        return productRepository.findByName(query.name());
    }

}
