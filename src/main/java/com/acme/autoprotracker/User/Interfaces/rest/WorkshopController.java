package com.acme.autoprotracker.User.Interfaces.rest;


import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Services.WorkshopCommandService;
import com.acme.autoprotracker.User.Domain.Services.WorkshopQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateWorkshopResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CustomerResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.WorkshopResource;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/workshop", produces = "application/json")
public class WorkshopController {
    private final WorkshopCommandService workshopCommandService;
    private final WorkshopQueryService workshopQueryService;
    private final CreateWorkshopCommandFromResourceAssembler createWorkshopCommandFromResourceAssembler;
    private final DeleteWorkshopCommandFromResourceAssembler deleteWorkshopCommandFromResourceAssembler;
    private final WorkshopResourceFromEntityAssembler workshopResourceFromEntityAssembler;

    private final WorkshopRepository workshopRepository;
    public WorkshopController(WorkshopCommandService workshopCommandService, WorkshopQueryService workshopQueryService,
                              CreateWorkshopCommandFromResourceAssembler createWorkshopCommandFromResourceAssembler,
                              DeleteWorkshopCommandFromResourceAssembler deleteWorkshopCommandFromResourceAssembler,
                              WorkshopResourceFromEntityAssembler workshopResourceFromEntityAssembler,
                              WorkshopRepository workshopRepository) {
        this.workshopCommandService = workshopCommandService;
        this.workshopQueryService = workshopQueryService;
        this.createWorkshopCommandFromResourceAssembler = createWorkshopCommandFromResourceAssembler;
        this.deleteWorkshopCommandFromResourceAssembler = deleteWorkshopCommandFromResourceAssembler;
        this.workshopResourceFromEntityAssembler = workshopResourceFromEntityAssembler;
        this.workshopRepository = workshopRepository;
    }

    @PostMapping
    public ResponseEntity<?> createWorkshop(@RequestBody CreateWorkshopResource createWorkshopResource) {
        if (!workshopQueryService.userExists(createWorkshopResource.userId())) {
            return ResponseEntity.badRequest().body("Invalid userId");
        }
        var createWorkshopCommand = createWorkshopCommandFromResourceAssembler.toCommandFromResource(createWorkshopResource);
        var Id = workshopCommandService.handle(createWorkshopCommand);
        var workshop = workshopQueryService.getWorkshopById(Id);
        if (workshop.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resource = workshopResourceFromEntityAssembler.toResourceFromEntity(workshop.get());
        return ResponseEntity.created(null).body(resource);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteWorkshop(@PathVariable Long Id) {
        var deleteWorkshopCommand = new DeleteWorkshopCommand(Id);
        workshopCommandService.handle(deleteWorkshopCommand);
        return ResponseEntity.ok("Workshop with given id successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<WorkshopResource>> getAllWorkshops() {
        var workshops = workshopQueryService.getAllWorkshop();
        var resources = workshops.stream().map(workshopResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<WorkshopResource> getWorkshopById(@PathVariable Long Id) {
        var workshop = workshopQueryService.getWorkshopById(Id);
        if (workshop.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = workshopResourceFromEntityAssembler.toResourceFromEntity(workshop.get());
        return ResponseEntity.ok(resource);
    }
}
