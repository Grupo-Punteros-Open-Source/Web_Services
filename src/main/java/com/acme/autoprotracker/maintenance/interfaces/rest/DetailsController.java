package com.acme.autoprotracker.maintenance.interfaces.rest;

import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.DetailCommandService;
import com.acme.autoprotracker.maintenance.domain.services.DetailQueryService;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.CreateDetailResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.DetailResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.UpdateDetailResource;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.CreateDetailCommandFromResourceAssembler;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.DetailResourceFromEntityAssembler;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.UpdateDetailCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/details", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Details", description = "Details Management Endpoints")
public class DetailsController {

    public DetailsController(DetailQueryService detailQueryService, DetailCommandService detailCommandService) {
        this.detailQueryService = detailQueryService;
        this.detailCommandService = detailCommandService;
    }

    private final DetailQueryService detailQueryService;
    private final DetailCommandService detailCommandService;



    /**
     * Creates a new detail.
     *
     * @param createDetailResource the resource containing the data for the detail to be created
     * @return the created detail resource
     * @see CreateDetailResource
     * @see DetailResource
     */
    @PostMapping
    public ResponseEntity<DetailResource> createDetail(@RequestBody CreateDetailResource createDetailResource) {
        var createDetailsCommand = CreateDetailCommandFromResourceAssembler.toCommandFromResource(createDetailResource);
        var detailId = detailCommandService.handle(createDetailsCommand);
        if (detailId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getDetailByIdQuery = new GetDetailByIdQuery(detailId);
        var detail = detailQueryService.handle(getDetailByIdQuery);
        if (detail.isEmpty()) return ResponseEntity.badRequest().build();
        var detailResource = DetailResourceFromEntityAssembler.toResourceFromEntity(detail.get());
        return new ResponseEntity<>(detailResource, HttpStatus.CREATED);
    }


    /**
     * Gets a detail by its id.
     *
     * @param detailId the id of the detail to be retrieved
     * @return the detail resource with the given id
     * @see DetailResource
     */
    @GetMapping("/{detailId}")
    public ResponseEntity<DetailResource> getDetailById(@PathVariable Long detailId) {
        var getDetailByIdQuery = new GetDetailByIdQuery(detailId);
        var detail = detailQueryService.handle(getDetailByIdQuery);
        if (detail.isEmpty()) return ResponseEntity.badRequest().build();
        var detailResource = DetailResourceFromEntityAssembler.toResourceFromEntity(detail.get());
        return ResponseEntity.ok(detailResource);
    }


    /**
     * Gets all the details.
     *
     * @return the list of all the details resources
     * @see DetailResource
     */
    @GetMapping
    public ResponseEntity<List<DetailResource>> getAllDetail() {
        var getAllDetailsQuery = new GetAllDetailsQuery();
        var courses = detailQueryService.handle(getAllDetailsQuery);
        var detailResources = courses.stream().map(DetailResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(detailResources);
    }

    /**
     * Updates a detail.
     *
     * @param detailId             the id of the detail to be updated
     * @param updateDetailResource the resource containing the data for the detail to be updated
     * @return the updated detail resource
     * @see UpdateDetailResource
     * @see DetailResource
     */
    @PutMapping("/{detailId}")
    public ResponseEntity<DetailResource> updateDetail(@PathVariable Long detailId, @RequestBody UpdateDetailResource updateDetailResource) {
        var updateDetailCommand = UpdateDetailCommandFromResourceAssembler.toCommandFromResource(detailId, updateDetailResource);
        var updatedDetail = detailCommandService.handle(updateDetailCommand);
        if (updatedDetail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var detailResource = DetailResourceFromEntityAssembler.toResourceFromEntity(updatedDetail.get());
        return ResponseEntity.ok(detailResource);
    }


    /**
     * Deletes a detail.
     *
     * @param detailId the id of the detail to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{detailId}")
    public ResponseEntity<?> deleteDetail(@PathVariable Long detailId) {
        var deleteDetailCommand = new DeleteDetailCommand(detailId);
        detailCommandService.handle(deleteDetailCommand);
        return ResponseEntity.ok("Detail with given id successfully deleted");
    }

}
