package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteWorkshopCommand;
import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Domain.Model.Entities.Workshop;
import com.acme.autoprotracker.User.Domain.Services.WorkshopCommandService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkshopCommandServiceImpl implements WorkshopCommandService {
    private final WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopCommandServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    public Long handle(CreateWorkshopCommand command) {
        Workshop workshop = new Workshop();
        workshop.setUserId(command.userId());
        // set other customer properties
        workshopRepository.save(workshop);
        return workshop.getId();
    }

    @Override
    public void handle(DeleteWorkshopCommand command) {
        if (!workshopRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Workshop does not exist");
        }
        try {
            workshopRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting workshop: " + e.getMessage());
        }
    }
}
