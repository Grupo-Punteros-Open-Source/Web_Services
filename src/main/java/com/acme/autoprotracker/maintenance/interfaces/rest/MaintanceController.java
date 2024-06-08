package com.acme.autoprotracker.maintenance.interfaces.rest;


import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteMaintanceCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteVehicleCommand;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllMaintanceQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllVehiclesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetMaintanceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetVehicleByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.MaintanceCommandService;
import com.acme.autoprotracker.maintenance.domain.services.MaintanceQueryService;
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
public class MaintanceController {
    private final MaintanceQueryService maintanceQueryService;
    private final MaintanceCommandService maintanceCommandService;

    public MaintanceController(MaintanceQueryService maintanceQueryService, MaintanceCommandService maintanceCommandService) {
        this.maintanceQueryService = maintanceQueryService;
        this.maintanceCommandService = maintanceCommandService;
    }


    /**
     * Creates a new maintance.
     *
     * @param createMaintanceResource the resource containing the data for the maintance to be created
     * @return the created maintance resource
     * @see CreateMaintanceResource
     * @see MaintanceResource
     */
    @PostMapping
    public ResponseEntity<MaintanceResource> createVehicle(@RequestBody CreateMaintanceResource createMaintanceResource) {
        var createMaintanceCommand = CreateMaintanceCommandFromResourceAssembler.toCommandFromResource(createMaintanceResource);
        var maintenanceId = maintanceCommandService.handle(createMaintanceCommand);
        if (maintenanceId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getMaintenanceByIdQuery = new GetMaintanceByIdQuery(maintenanceId);
        var maintenance = maintanceQueryService.handle(getMaintenanceByIdQuery);
        if (maintenance.isEmpty()) return ResponseEntity.badRequest().build();
        var maintenanceResource = MaintanceResourceFromEntityAssembler.toResourceFromEntity(maintenance.get());
        return new ResponseEntity<>(maintenanceResource, HttpStatus.CREATED);
    }


    /**
     * Gets a maintenance by its id.
     *
     * @param maintenanceId the id of the maintenance to be retrieved
     * @return the maintenance resource with the given id
     * @see MaintanceResource
     */
    @GetMapping("/{maintenanceId}")
    public ResponseEntity<MaintanceResource> getMaintenanceById(@PathVariable Long maintenanceId) {
        var getMaintenanceByIdQuery = new GetMaintanceByIdQuery(maintenanceId);
        var maintenance = maintanceQueryService.handle(getMaintenanceByIdQuery);
        if (maintenance.isEmpty()) return ResponseEntity.badRequest().build();
        var maintenanceResource = MaintanceResourceFromEntityAssembler.toResourceFromEntity(maintenance.get());
        return ResponseEntity.ok(maintenanceResource);
    }


    /**
     * Gets all the maintenances.
     *
     * @return the list of all the maintenances resources
     * @see MaintanceResource
     */
    @GetMapping
    public ResponseEntity<List<MaintanceResource>> getAllMaintenances() {
        var getAllMaintenancesQuery = new GetAllMaintanceQuery();
        var maintenances = maintanceQueryService.handle(getAllMaintenancesQuery);
        var maintenanceResources = maintenances.stream().map(MaintanceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(maintenanceResources);
    }

    /**
     * Updates a maintenance.
     *
     * @param maintenanceId             the id of the maintenance to be updated
     * @param updateMaintenanceResource the resource containing the data for the maintenance to be updated
     * @return the updated maintenance resource
     * @see UpdatedMaintanceResource
     * @see MaintanceResource
     */
    @PutMapping("/{maintenanceId}")
    public ResponseEntity<MaintanceResource> updateMaintenance(@PathVariable Long maintenanceId, @RequestBody UpdatedMaintanceResource updateMaintenanceResource) {
        var updateMaintenanceCommand = UpdateMaintanceCommandFromResourceAssembler.toCommandFromResource(maintenanceId, updateMaintenanceResource);
        var updatedMaintenance = maintanceCommandService.handle(updateMaintenanceCommand);
        if (updatedMaintenance.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var maintenanceResource = MaintanceResourceFromEntityAssembler.toResourceFromEntity(updatedMaintenance.get());
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
        var deleteMaintenanceCommand = new DeleteMaintanceCommand(maintenanceId);
        maintanceCommandService.handle(deleteMaintenanceCommand);
        return ResponseEntity.ok("Maintenance with given id successfully deleted");
    }



}

