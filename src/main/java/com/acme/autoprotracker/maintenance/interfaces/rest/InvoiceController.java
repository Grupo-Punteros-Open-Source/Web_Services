package com.acme.autoprotracker.maintenance.interfaces.rest;


import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.DeleteInvoiceCommand;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllDetailsQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetAllInvoicesQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetDetailByIdQuery;
import com.acme.autoprotracker.maintenance.domain.model.queries.GetInvoiceByIdQuery;
import com.acme.autoprotracker.maintenance.domain.services.DetailCommandService;
import com.acme.autoprotracker.maintenance.domain.services.DetailQueryService;
import com.acme.autoprotracker.maintenance.domain.services.InvoiceCommandService;
import com.acme.autoprotracker.maintenance.domain.services.InvoiceQueryService;
import com.acme.autoprotracker.maintenance.interfaces.rest.resources.*;
import com.acme.autoprotracker.maintenance.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/invoices", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Invoices", description = "Invoices Management Endpoints")
public class InvoiceController {

    public InvoiceController(InvoiceQueryService invoiceQueryService, InvoiceCommandService invoiceCommandService) {
        this.invoiceQueryService = invoiceQueryService;
        this.invoiceCommandService = invoiceCommandService;
    }

    private final InvoiceQueryService invoiceQueryService;
    private final InvoiceCommandService invoiceCommandService;



    /**
     * Creates a new invoice.
     *
     * @param createInvoiceResource the resource containing the data for the invoice to be created
     * @return the created invoice resource
     * @see CreateInvoiceResource
     * @see InvoiceResource
     */
    @PostMapping
    public ResponseEntity<InvoiceResource> createInvoice(@RequestBody CreateInvoiceResource createInvoiceResource) {
        var createInvoiceCommand = CreateInvoiceCommandFromResourceAssembler.toCommandFromResource(createInvoiceResource);
        var invoiceId = invoiceCommandService.handle(createInvoiceCommand);
        if (invoiceId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getInvoiceByIdQuery = new GetInvoiceByIdQuery(invoiceId);
        var invoice = invoiceQueryService.handle(getInvoiceByIdQuery);
        if (invoice.isEmpty()) return ResponseEntity.badRequest().build();
        var invoiceResource = InvoiceResourceFromEntityAssembler.toResourceFromEntity(invoice.get());
        return new ResponseEntity<>(invoiceResource, HttpStatus.CREATED);
    }


    /**
     * Gets an invoice by its id.
     *
     * @param invoiceId the id of the invoice to be retrieved
     * @return the invoice resource with the given id
     * @see InvoiceResource
     */
    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceResource> getInvoiceById(@PathVariable Long invoiceId) {
        var getInvoiceByIdQuery = new GetInvoiceByIdQuery(invoiceId);
        var invoice = invoiceQueryService.handle(getInvoiceByIdQuery);
        if (invoice.isEmpty()) return ResponseEntity.badRequest().build();
        var invoiceResource = InvoiceResourceFromEntityAssembler.toResourceFromEntity(invoice.get());
        return ResponseEntity.ok(invoiceResource);
    }


    /**
     * Gets all the invoices.
     *
     * @return the list of all the invoices resources
     * @see InvoiceResource
     */
    @GetMapping
    public ResponseEntity<List<InvoiceResource>> getAllInvoices() {
        var getAllInvoicesQuery = new GetAllInvoicesQuery();
        var courses = invoiceQueryService.handle(getAllInvoicesQuery);
        var invoiceResources = courses.stream().map(InvoiceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(invoiceResources);
    }

    /**
     * Updates an invoice.
     *
     * @param invoiceId             the id of the invoice to be updated
     * @param updateInvoiceResource the resource containing the data for the invoice to be updated
     * @return the updated invoice resource
     * @see UpdateInvoiceResource
     * @see InvoiceResource
     */
    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceResource> updateInvoice(@PathVariable Long invoiceId, @RequestBody UpdateInvoiceResource updateInvoiceResource) {
        var updateInvoiceCommand = UpdateInvoiceCommandFromResourceAssembler.toCommandFromResource(invoiceId, updateInvoiceResource);
        var updatedInvoice = invoiceCommandService.handle(updateInvoiceCommand);
        if (updatedInvoice.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var invoiceResource = InvoiceResourceFromEntityAssembler.toResourceFromEntity(updatedInvoice.get());
        return ResponseEntity.ok(invoiceResource);
    }


    /**
     * Deletes an invoice.
     *
     * @param invoiceId the id of the invoice to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long invoiceId) {
        var deleteInvoiceCommand = new DeleteInvoiceCommand(invoiceId);
        invoiceCommandService.handle(deleteInvoiceCommand);
        return ResponseEntity.ok("Invoice with given id successfully deleted");
    }
}
