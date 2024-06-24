package com.acme.autoprotracker.maintenance.interfaces.rest;


import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteMaintenanceCommand;
import com.acme.autoprotracker.maintenance.domain.model.queries.*;
import com.acme.autoprotracker.maintenance.domain.services.MaintenanceCommandService;
import com.acme.autoprotracker.maintenance.domain.services.MaintenanceQueryService;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.*;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/maintances", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Maintance", description = "Maintance Management Endpoints")
public class MaintenanceController {
    private final MaintenanceQueryService maintanceQueryService;
    private final MaintenanceCommandService maintanceCommandService;

    public MaintenanceController(MaintenanceQueryService maintanceQueryService, MaintenanceCommandService maintanceCommandService) {
        this.maintanceQueryService = maintanceQueryService;
        this.maintanceCommandService = maintanceCommandService;
    }


    /**
     * Creates a new maintance.
     *
     * @param createMaintanceResource the resource containing the data for the maintance to be created
     * @return the created maintance resource
     * @see CreateMaintenanceResource
     * @see MaintenanceResource
     */
    @PostMapping
    public ResponseEntity<MaintenanceResource> createVehicle(@RequestBody CreateMaintenanceResource createMaintanceResource) {
        var createMaintenanceCommand = CreateMaintenanceCommandFromResourceAssembler.toCommandFromResource(createMaintanceResource);
        var maintenanceId = maintanceCommandService.handle(createMaintenanceCommand);
        if (maintenanceId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getMaintenanceByIdQuery = new GetMaintenanceByIdQuery(maintenanceId);
        var maintenance = maintanceQueryService.handle(getMaintenanceByIdQuery);
        if (maintenance.isEmpty()) return ResponseEntity.badRequest().build();
        var maintenanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(maintenance.get());
        return new ResponseEntity<>(maintenanceResource, HttpStatus.CREATED);
    }


    /**
     * Gets a maintenance by its id.
     *
     * @param maintenanceId the id of the maintenance to be retrieved
     * @return the maintenance resource with the given id
     * @see MaintenanceResource
     */
    @GetMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceResource> getMaintenanceById(@PathVariable Long maintenanceId) {
        var getMaintenanceByIdQuery = new GetMaintenanceByIdQuery(maintenanceId);
        var maintenance = maintanceQueryService.handle(getMaintenanceByIdQuery);
        if (maintenance.isEmpty()) return ResponseEntity.badRequest().build();
        var maintenanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(maintenance.get());
        return ResponseEntity.ok(maintenanceResource);
    }


    /**
     * Gets a maintance by its name.
     *
     * @param vehicleId the vehicleId of the maintance to be retrieved
     * @return the product resource with the given name
     * @see MaintenanceResource
     */
   /* @GetMapping("/getBy/{vehicleId}")
    public ResponseEntity<MaintenanceResource> getMaintanceByVehicleId(@PathVariable Long vehicleId) {
        var getMaintanceByVehicleIdQuery = new GetMaintenanceByVehicleIdQuery(vehicleId);
        var maintance = maintanceQueryService.handle(getMaintanceByVehicleIdQuery);
        if (maintance.isEmpty()) return ResponseEntity.badRequest().build();
        var maintanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(maintance.get());
        return ResponseEntity.ok(maintanceResource);
    }*/

    /**
     * Gets all the maintenances.
     *
     * @return the list of all the maintenances resources
     * @see MaintenanceResource
     */
    @GetMapping
    public ResponseEntity<List<MaintenanceResource>> getAllMaintenances() {
        var getAllMaintenancesQuery = new GetAllMaintenanceQuery();
        var maintenances = maintanceQueryService.handle(getAllMaintenancesQuery);
        var maintenanceResources = maintenances.stream().map(MaintenanceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(maintenanceResources);
    }

    /**
     * Updates a maintenance.
     *
     * @param maintenanceId             the id of the maintenance to be updated
     * @param updateMaintenanceResource the resource containing the data for the maintenance to be updated
     * @return the updated maintenance resource
     * @see UpdatedMaintenanceResource
     * @see MaintenanceResource
     */
    @PutMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceResource> updateMaintenance(@PathVariable Long maintenanceId, @RequestBody UpdatedMaintenanceResource updateMaintenanceResource) {
        var updateMaintenanceCommand = UpdateMaintenanceCommandFromResourceAssembler.toCommandFromResource(maintenanceId, updateMaintenanceResource);
        var updatedMaintenance = maintanceCommandService.handle(updateMaintenanceCommand);
        if (updatedMaintenance.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var maintenanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(updatedMaintenance.get());
        return ResponseEntity.ok(maintenanceResource);
    }


    /**
     * Deletes a maintenance.
     *
     * @param maintenanceId the id of the maintenance to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<?> deleteMaintenance(@PathVariable Long maintenanceId) {
        var deleteMaintenanceCommand = new DeleteMaintenanceCommand(maintenanceId);
        maintanceCommandService.handle(deleteMaintenanceCommand);
        return ResponseEntity.ok("Maintenance with given id successfully deleted");
    }



}

