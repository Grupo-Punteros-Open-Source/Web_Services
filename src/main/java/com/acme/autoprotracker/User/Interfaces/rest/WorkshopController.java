package com.acme.autoprotracker.User.Interfaces.rest;

import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllWorkshopQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetWorkshopByIdQuery;
import com.acme.autoprotracker.User.Domain.Services.WorkshopCommandService;
import com.acme.autoprotracker.User.Domain.Services.WorkshopQueryService;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.*;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/workshops", produces = "application/json")
@Tag(name = "Workshops", description = "Workshop Management Endpoints")
public class WorkshopController {

    private final WorkshopCommandService workshopCommandService;
    private final WorkshopQueryService workshopQueryService;

    public WorkshopController(WorkshopCommandService workshopCommandService, WorkshopQueryService workshopQueryService) {
        this.workshopCommandService = workshopCommandService;
        this.workshopQueryService = workshopQueryService;
    }

    @PostMapping
    public ResponseEntity<WorkshopResource> createWorkshop(@RequestBody CreateWorkshopResource createWorkshopResource) {
        var createWorkshopCommand = CreateWorkshopCommandFromResourceAssembler.toCommandFromResource(createWorkshopResource);
        var workshopId = workshopCommandService.handle(createWorkshopCommand);
        if (workshopId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getWorkshopByIdQuery = new GetWorkshopByIdQuery(workshopId);
        var workshop = workshopQueryService.handle(getWorkshopByIdQuery);
        if (workshop.isEmpty()) return ResponseEntity.badRequest().build();
        var workshopResource = WorkshopResourceFromEntityAssembler.toResourceFromEntity(workshop.get());
        return new ResponseEntity<>(workshopResource, HttpStatus.CREATED);
    }

    @GetMapping("/{workshopId}")
    public ResponseEntity<WorkshopResource> getWorkshopById(@PathVariable Long workshopId) {
        var getWorkshopByIdQuery = new GetWorkshopByIdQuery(workshopId);
        var workshop = workshopQueryService.handle(getWorkshopByIdQuery);
        if (workshop.isEmpty()) return ResponseEntity.badRequest().build();
        var workshopResource = WorkshopResourceFromEntityAssembler.toResourceFromEntity(workshop.get());
        return ResponseEntity.ok(workshopResource);
    }

    @GetMapping
    public ResponseEntity<List<WorkshopResource>> getAllWorkshop() {
        var getAllWorkshopsQuery = new GetAllWorkshopQuery();
        var workshops = workshopQueryService.handle(getAllWorkshopsQuery);
        var workshopResources = workshops.stream().map(WorkshopResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(workshopResources);
    }

    @PutMapping("/{workshopId}")
    public ResponseEntity<WorkshopResource> updateworkshop(@PathVariable Long workshopId, @RequestBody UpdateWorkshopResource updateWorkshopResource) {
        var updateWorkshopCommand = UpdateWorkshopCommandFromResourceAssembler.toCommandFromResource(updateWorkshopResource, workshopId);
        var updatedWorkshop = workshopCommandService.handle(updateWorkshopCommand);
        if (updatedWorkshop.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var workshopResource = WorkshopResourceFromEntityAssembler.toResourceFromEntity(updatedWorkshop.get());
        return ResponseEntity.ok(workshopResource);
    }

    @DeleteMapping("/{workshopId}")
    public ResponseEntity<?> deleteWorkshop(@PathVariable Long workshopId) {
        var deleteWorkshopCommand = new DeleteWorkshopCommand(workshopId);
        workshopCommandService.handle(deleteWorkshopCommand);
        return ResponseEntity.ok("Workshop with given id successfully deleted");
    }
}
