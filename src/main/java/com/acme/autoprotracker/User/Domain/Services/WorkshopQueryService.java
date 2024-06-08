package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Entities.Customer;
import com.acme.autoprotracker.User.Domain.Model.Entities.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopQueryService {
    public boolean userExists(Long userId);
    Optional<Workshop> getWorkshopById(Long id);
    List<Workshop> getAllWorkshop();
}
