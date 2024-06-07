package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
}