package com.acme.autoprotracker.User.Application.internal.queryServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Domain.Model.Entities.Workshop;
import com.acme.autoprotracker.User.Domain.Services.WorkshopQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopQueryServiceImpl implements WorkshopQueryService {
    private final WorkshopRepository workshopRepository;
    private final UserRepository userRepository;

    @Autowired
    public WorkshopQueryServiceImpl(WorkshopRepository workshopRepository, UserRepository userRepository) {
        this.workshopRepository = workshopRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Workshop> getWorkshopById(Long id) {
        return workshopRepository.findById(id);
    }

    @Override
    public List<Workshop> getAllWorkshop() {
        return workshopRepository.findAll();
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }



}
