package com.acme.autoprotracker.maintenance.interfaces.rest;

import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteInvoiceCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteVehicleCommand;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllInvoicesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllVehiclesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetInvoiceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetVehicleByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.VehicleCommandService;
import com.acme.autoprotracker.maintenance.domain.services.VehicleQueryService;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.*;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping(value = "/api/v1/vehicles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Vehicles", description = "Vehicles Management Endpoints")
public class VehiclesController {
    public VehiclesController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommandService) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommandService;
    }

    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;



    /**
     * Creates a new vehcile.
     *
     * @param createVehicleResource the resource containing the data for the vehicle to be created
     * @return the created vehicle resource
     * @see CreateVehicleResource
     * @see VehicleResource
     */
    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource createVehicleResource) {
        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(createVehicleResource);
        var vehicleId = vehicleCommandService.handle(createVehicleCommand);
        if (vehicleId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
    }


    /**
     * Gets a vehicle by its id.
     *
     * @param vehicleId the id of the vehicle to be retrieved
     * @return the vehicle resource with the given id
     * @see VehicleResource
     */
    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }


    /**
     * Gets all the vehicles.
     *
     * @return the list of all the vehicles resources
     * @see VehicleResource
     */
    @GetMapping
    public ResponseEntity<List<VehicleResource>> getAllVehicles() {
        var getAllVehiclesQuery = new GetAllVehiclesQuery();
        var courses = vehicleQueryService.handle(getAllVehiclesQuery);
        var vehicleResources = courses.stream().map(VehicleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(vehicleResources);
    }

    /**
     * Updates a vehicle.
     *
     * @param vehicleId             the id of the vehicle to be updated
     * @param updateVehicleResource the resource containing the data for the vehicle to be updated
     * @return the updated vehicle resource
     * @see UpdateVehiclesResource
     * @see VehicleResource
     */
    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResource> updateVehicle(@PathVariable Long vehicleId, @RequestBody UpdateVehiclesResource updateVehicleResource) {
        var updateVehicleCommand = UpdateVehicleCommandFromResourceAssembler.toCommandFromResource(vehicleId, updateVehicleResource);
        var updatedVehicle = vehicleCommandService.handle(updateVehicleCommand);
        if (updatedVehicle.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(updatedVehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }


    /**
     * Deletes a vehicle.
     *
     * @param vehicleId the id of the vehicle to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
        var deleteVehicleCommand = new DeleteVehicleCommand(vehicleId);
        vehicleCommandService.handle(deleteVehicleCommand);
        return ResponseEntity.ok("Vehicle with given id successfully deleted");
    }

}
