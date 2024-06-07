package com.acme.autoprotracker.workshop.interfaces.rest;


import com.acme.autoprotracker.workshop.domain.model.commands.DeleteProductCommand;
import com.acme.autoprotracker.workshop.domain.model.queries.GetAllProductsQuery;
import com.acme.autoprotracker.workshop.domain.model.queries.GetProductByIdQuery;
import com.acme.autoprotracker.workshop.domain.model.queries.GetProductByNameQuery;
import com.acme.autoprotracker.workshop.domain.services.ProductCommandService;
import com.acme.autoprotracker.workshop.domain.services.ProductQueryService;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.CreateProductResource;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.ProductResource;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.UpdateProductResource;
import com.acme.autoprotracker.workshop.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.acme.autoprotracker.workshop.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.acme.autoprotracker.workshop.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Products Management Endpoints")
public class ProductController {
    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public ProductController(ProductQueryService productQueryService, ProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }


    /**
     * Creates a new product.
     *
     * @param createProductResource the resource containing the data for the product to be created
     * @return the created product resource
     * @see CreateProductResource
     * @see ProductResource
     */
    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(createProductResource);
        var productId = productCommandService.handle(createProductCommand);
        if (productId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }




    /**
     * Gets a product by its id.
     *
     * @param productId the id of the product to be retrieved
     * @return the product resource with the given id
     * @see ProductResource
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource> geProductById(@PathVariable Long productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }


    /**
     * Gets a product by its name.
     *
     * @param name the name of the product to be retrieved
     * @return the product resource with the given name
     * @see ProductResource
     */
    @GetMapping("/getBy/{name}")
    public ResponseEntity<ProductResource> geProductByName(@PathVariable String name) {
        var getProductByNameQuery = new GetProductByNameQuery(name);
        var product = productQueryService.handle(getProductByNameQuery);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }


    /**
     * Gets all the products.
     *
     * @return the list of all the products resources
     * @see ProductResource
     */
    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductsQuery = new GetAllProductsQuery();
        var courses = productQueryService.handle(getAllProductsQuery);
        var productResources = courses.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);
    }


    /**
     * Updates a product.
     *
     * @param productId             the id of the product to be updated
     * @param updateProductResource the resource containing the data for the product to be updated
     * @return the updated product resource
     * @see UpdateProductResource
     * @see ProductResource
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource updateProductResource) {
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(productId, updateProductResource);
        var updatedProduct = productCommandService.handle(updateProductCommand);
        if (updatedProduct.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(updatedProduct.get());
        return ResponseEntity.ok(productResource);
    }


    /**
     * Deletes a product.
     *
     * @param productId the id of the product to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deletePorduct(@PathVariable Long productId) {
        var deleteProductCommand = new DeleteProductCommand(productId);
        productCommandService.handle(deleteProductCommand);
        return ResponseEntity.ok("Product with given id successfully deleted");
    }




}
