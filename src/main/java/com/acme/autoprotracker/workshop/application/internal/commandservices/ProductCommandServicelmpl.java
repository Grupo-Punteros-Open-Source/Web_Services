package com.acme.autoprotracker.workshop.application.internal.commandservices;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Product;
import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.DeleteProductCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.UpdateProductCommand;
import com.acme.autoprotracker.workshop.domain.services.ProductCommandService;
import com.acme.autoprotracker.workshop.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServicelmpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductCommandServicelmpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Long handle(CreateProductCommand command) {
        if (productRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Product with same name already exists");
        }
        var product = new Product(command);
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return product.getId();
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        var result = productRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Product does not exist");
        var productToUpdate = result.get();
        try {
            var productUpdated = productRepository.save(productToUpdate.updateProduct(command.name(), command.quantity(), command.price(), command.productImage(), command.workshopId()));
            return Optional.of(productUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if (!productRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Product does not exist");
        }
        try {
            productRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting product: " + e.getMessage());
        }

    }
}
