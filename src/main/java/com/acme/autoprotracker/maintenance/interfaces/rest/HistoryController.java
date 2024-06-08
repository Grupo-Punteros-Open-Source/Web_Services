package com.acme.autoprotracker.maintenance.interfaces.rest;

import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteHistoryCommand;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllHistoriesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetHistoryByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.HistoryCommandService;
import com.acme.autoprotracker.maintenance.domain.services.HistoryQueryService;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.*;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/history", produces = APPLICATION_JSON_VALUE)
@Tag(name = "History", description = "Histories Management Endpoints")
public class HistoryController {

    public HistoryController(HistoryQueryService historyQueryService, HistoryCommandService historyCommandService) {
        this.historyQueryService = historyQueryService;
        this.historyCommandService = historyCommandService;
    }

    private final HistoryQueryService historyQueryService;
    private final HistoryCommandService historyCommandService;



    @PostMapping
    public ResponseEntity<HistoryResource> createHistory(@RequestBody CreateHistoryResource createHistoryResource) {
        var createHistoriesCommand = CreateHistoryCommandFromResourceAssembler.toCommandFromResource(createHistoryResource);
        var historyId = historyCommandService.handle(createHistoriesCommand);
        if (historyId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getHistoryByIdQuery = new GetHistoryByIdQuery(historyId);
        var history = historyQueryService.handle(getHistoryByIdQuery);
        if (history.isEmpty()) return ResponseEntity.badRequest().build();
        var detailResource = HistoryResourceFromEntityAssembler.toResourceFromEntity(history.get());
        return new ResponseEntity<>(detailResource, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoryResource> getHistoryById(@PathVariable Long historyId) {
        var getHistoryByIdQuery = new GetHistoryByIdQuery(historyId);
        var history = historyQueryService.handle(getHistoryByIdQuery);
        if (history.isEmpty()) return ResponseEntity.badRequest().build();
        var historyResource = HistoryResourceFromEntityAssembler.toResourceFromEntity(history.get());
        return ResponseEntity.ok(historyResource);
    }


    @GetMapping
    public ResponseEntity<List<HistoryResource>> getAllHistory() {
        var getAllHistoriesQuery = new GetAllHistoriesQuery();
        var histories = historyQueryService.handle(getAllHistoriesQuery);
        var historyResources = histories.stream().map(HistoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(historyResources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoryResource> updateHistory(@PathVariable Long historyId, @RequestBody UpdateHistoryResource updateHistoryResource) {
        var updateHistoyCommand = UpdateHistoryCommandFromResourceAssembler.toCommandFromResource(historyId, updateHistoryResource);
        var updatedHistory = historyCommandService.handle(updateHistoyCommand);
        if (updatedHistory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var historyResource = HistoryResourceFromEntityAssembler.toResourceFromEntity(updatedHistory.get());
        return ResponseEntity.ok(historyResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long historyId) {
        var deleteHistoryCommand = new DeleteHistoryCommand(historyId);
        historyCommandService.handle(deleteHistoryCommand);
        return ResponseEntity.ok("History with given id successfully deleted");
    }

}
