package com.acme.autoprotracker.maintenance.application.internal.commandservices;

import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import com.acme.autoprotracker.maintenance.domain.model.aggregates.Maintenance;
import com.acme.autoprotracker.maintenance.domain.model.commands.*;
import com.acme.autoprotracker.maintenance.domain.services.MaintenanceCommandService;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.HistoryRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import com.acme.autoprotracker.maintenance.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MaintenanceCommandServicelmpl implements MaintenanceCommandService {

    public MaintenanceCommandServicelmpl(MaintenanceRepository maintenanceRepository, InvoiceRepository invoiceRepository, CustomerRepository customerRepository, WorkshopRepository workshopRepository, VehicleRepository vehicleRepository, HistoryRepository historyRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.workshopRepository = workshopRepository;
        this.vehicleRepository = vehicleRepository;
        this.historyRepository = historyRepository;
    }

    private final MaintenanceRepository maintenanceRepository;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final WorkshopRepository workshopRepository;
    private final VehicleRepository vehicleRepository;
    private final HistoryRepository historyRepository;

    @Override
    public Long handle(CreateMaintenanceCommand command) {

        var invoiceResult = invoiceRepository.findById(command.workshopId());
        var invoice = invoiceResult.get();

        var customerResult = customerRepository.findById(command.workshopId());
        var customer = customerResult.get();

        var workshopResult = workshopRepository.findById(command.workshopId());
        var workshop = workshopResult.get();

        var vehicleResult = vehicleRepository.findById(command.workshopId());
        var vehicle = vehicleResult.get();

        var historyResult = historyRepository.findById(command.workshopId());
        var history = historyResult.get();

        var maintenance = new Maintenance(command, invoice, customer, workshop, vehicle, history);
        try {
            maintenanceRepository.save(maintenance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving maintenance: " + e.getMessage());
        }
        return maintenance.getId();
    }

    @Override
    public Optional<Maintenance> handle(UpdateMaintenanceCommand command) {
        var result = maintenanceRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Maintenance does not exist");
        var maintenanceToUpdate = result.get();

        var invoiceResult = invoiceRepository.findById(command.id());
        if (invoiceResult.isEmpty()) throw new IllegalArgumentException("Invoice does not exist");
        var invoice = invoiceResult.get();

        var customerResult = customerRepository.findById(command.id());
        if (customerResult.isEmpty()) throw new IllegalArgumentException("Customer does not exist");
        var customer = customerResult.get();

        var workshopResult = workshopRepository.findById(command.id());
        if (workshopResult.isEmpty()) throw new IllegalArgumentException("Workshop does not exist");
        var workshop = workshopResult.get();

        var vehicleResult = vehicleRepository.findById(command.id());
        if (vehicleResult.isEmpty()) throw new IllegalArgumentException("Vehicle does not exist");
        var vehicle = vehicleResult.get();

        var historyResult = historyRepository.findById(command.id());
        if (historyResult.isEmpty()) throw new IllegalArgumentException("History does not exist");
        var history = historyResult.get();

        try {
            var maintenanceUpdated = maintenanceRepository.save(maintenanceToUpdate.updateMaintenance(command.status(),
                    command.lastVisitDate(),command.comment(), invoice, customer, workshop, vehicle, history));
            return Optional.of(maintenanceUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating maintenance: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMaintenanceCommand command) {
        if (!maintenanceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Maintenance does not exist");
        }
        try {
            maintenanceRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting maintenance " + e.getMessage());
        }

    }


}
