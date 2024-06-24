package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Exceptions.UserNotFoundException;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.User.Domain.Model.Commands.*;
import com.acme.autoprotracker.User.Domain.Services.WorkshopCommandService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import com.acme.autoprotracker.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class WorkshopCommandServiceImpl implements WorkshopCommandService {

    private final WorkshopRepository workshopRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public WorkshopCommandServiceImpl(WorkshopRepository workshopRepository, UserRepository userRepository, CustomerRepository customerRepository) {
        this.workshopRepository = workshopRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Long handle(CreateWorkshopCommand command) {
        var userResult = userRepository.findById(command.userId());
        if (userResult.isEmpty()) throw new UserNotFoundException(command.userId());
        var user = userResult.get();

        var existingCustomer = customerRepository.findByUser(user);
        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("A customer with the same userId already exists");
        }
        var existingWorkshop = workshopRepository.findByUser(user);
        if (existingWorkshop.isPresent()) {
            throw new IllegalArgumentException("A workshop with the same userId already exists");
        }

        Workshop workshop = new Workshop(command, user);
        try {
            workshopRepository.save(workshop);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving workshop: " + e.getMessage());
        }
        return workshop.getId();
    }

    @Override
    public Optional<Workshop> handle(UpdateWorkshopCommand command) {
        var result = workshopRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Workshop does not exist");
        var workshopToUpdate = result.get();

        var userResult = userRepository.findById(command.id());
        if (userResult.isEmpty()) throw new UserNotFoundException(command.id());
        var user = userResult.get();

        try {
            var updatedWorkshop = workshopRepository.save(workshopToUpdate.update(
                    user,
                    command.name(),
                    command.address(),
                    command.phone(),
                    command.email(),
                    command.imageUrl()
            ));
            return Optional.of(updatedWorkshop);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating workshop: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteWorkshopCommand command) {
        if (!workshopRepository.existsById(command.workshopId())) {
            throw new IllegalArgumentException("Workshop does not exist");
        }
        try {
            workshopRepository.deleteById(command.workshopId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting workshop: " + e.getMessage());
        }
    }
}
