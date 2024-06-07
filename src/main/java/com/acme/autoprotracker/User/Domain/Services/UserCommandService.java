package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateUserCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command);
}