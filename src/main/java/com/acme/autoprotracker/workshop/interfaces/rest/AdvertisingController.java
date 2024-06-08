package com.acme.autoprotracker.workshop.interfaces.rest;

import com.acme.autoprotracker.workshop.domain.model.commands.DeleteAdvertisingCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.DeleteProductCommand;
import com.acme.autoprotracker.workshop.domain.model.queries.*;
import com.acme.autoprotracker.workshop.domain.services.AdvertisingCommandService;
import com.acme.autoprotracker.workshop.domain.services.AdvertisingQueryService;
import com.acme.autoprotracker.workshop.domain.services.ProductCommandService;
import com.acme.autoprotracker.workshop.domain.services.ProductQueryService;
import com.acme.autoprotracker.workshop.interfaces.rest.resources.*;
import com.acme.autoprotracker.workshop.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/advertisings", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Advertising", description = "Advertising Management Endpoints")
public class AdvertisingController {
    private final AdvertisingQueryService advertisingQueryService;
    private final AdvertisingCommandService advertisingCommandService;
    public AdvertisingController(AdvertisingQueryService advertisingQueryService, AdvertisingCommandService advertisingCommandService) {
        this.advertisingQueryService = advertisingQueryService;
        this.advertisingCommandService = advertisingCommandService;
    }

    /**
     * Creates a new advertising.
     *
     * @param createAdvertingResource the resource containing the data for the advertising to be created
     * @return the created advertising resource
     * @see CreateAdvertisingResource
     * @see AdvertisingResource
     */
    @PostMapping
    public ResponseEntity<AdvertisingResource> createAdvertising(@RequestBody CreateAdvertisingResource createAdvertingResource) {
        var createAdvertisingCommand = CreateAdvertisingCommandFromResourceAssembler.toCommandFromResource(createAdvertingResource);
        var advertisingId = advertisingCommandService.handle(createAdvertisingCommand);
        if (advertisingId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAdvertisingByIdQuery = new GetAdvertisingByIdQuery(advertisingId);
        var advertising = advertisingQueryService.handle(getAdvertisingByIdQuery);
        if (advertising.isEmpty()) return ResponseEntity.badRequest().build();
        var advertisingResource = AdvertisingResourceFromEntityAssembler.toResourceFromEntity(advertising.get());
        return new ResponseEntity<>(advertisingResource, HttpStatus.CREATED);
    }




    /**
     * Gets an advertising by its id.
     *
     * @param advertisingId the id of the advertising to be retrieved
     * @return the advertising resource with the given id
     * @see AdvertisingResource
     */
    @GetMapping("/{advertisingId}")
    public ResponseEntity<AdvertisingResource> geAdvertisingbyId(@PathVariable Long advertisingId) {
        var getAdvertisingByIdQuery = new GetAdvertisingByIdQuery(advertisingId);
        var advertising = advertisingQueryService.handle(getAdvertisingByIdQuery);
        if (advertising.isEmpty()) return ResponseEntity.badRequest().build();
        var advertisingResource = AdvertisingResourceFromEntityAssembler.toResourceFromEntity(advertising.get());
        return ResponseEntity.ok(advertisingResource);
    }


    /**
     * Gets all the advertising
     *
     * @return the list of all the advertising resources
     * @see ProductResource
     */
    @GetMapping
    public ResponseEntity<List<AdvertisingResource>> getAllAdvertising() {
        var getAllAdvertisingQuery = new GetAllAdvertisingQuery();
        var courses = advertisingQueryService.handle(getAllAdvertisingQuery);
        var advertisingResources = courses.stream().map(AdvertisingResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(advertisingResources);
    }


    /**
     * Updates an advertising.
     *
     * @param advertisingId           the id of the advertising to be updated
     * @param updateAdvertisingResource the resource containing the data for the advertising to be updated
     * @return the updated advertising resource
     * @see UpdateAdvertisingResource
     * @see AdvertisingResource
     */
    @PutMapping("/{advertisingId}")
    public ResponseEntity<AdvertisingResource> updateAdvertising(@PathVariable Long advertisingId, @RequestBody UpdateAdvertisingResource updateAdvertisingResource) {
        var updateAdvertisingCommand = UpdateAdvertisingCommandFromResourceAssembler.toCommandFromResource(advertisingId, updateAdvertisingResource);

        var updatedAdvertising = advertisingCommandService.handle(updateAdvertisingCommand);
        if (updatedAdvertising.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var advertisingResource = AdvertisingResourceFromEntityAssembler.toResourceFromEntity(updatedAdvertising.get());
        return ResponseEntity.ok(advertisingResource);
    }


    /**
     * Deletes an advertising.
     *
     * @param advertisingId the id of the product to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{advertisingId}")
    public ResponseEntity<?> deleteAdvertising(@PathVariable Long advertisingId) {
        var deleteAdvertisingCommand = new DeleteAdvertisingCommand(advertisingId);
        advertisingCommandService.handle(deleteAdvertisingCommand);
        return ResponseEntity.ok("Advertising with given id successfully deleted");
    }



}
