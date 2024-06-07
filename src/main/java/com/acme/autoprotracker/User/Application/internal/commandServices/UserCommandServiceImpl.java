package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateUserCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateUserCommand;
import com.acme.autoprotracker.User.Domain.Model.ValueObjects.UserAuthentication;
import com.acme.autoprotracker.User.Domain.Model.ValueObjects.UserData;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.autoprotracker.User.Domain.Services.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Autowired
    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (command.name() == null || command.name().isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        UserData userData = new UserData(command.name(), command.address(), command.phone(), command.email(), command.imageUrl());
        UserAuthentication userAuthentication = new UserAuthentication(command.username(), command.password());
        User user = new User(userData, userAuthentication);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        if (command.name() == null || command.name().isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        Optional<User> userOptional = userRepository.findById(command.id());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserData userData = new UserData(command.name(), command.address(), command.phone(), command.email(), command.imageUrl());
            UserAuthentication userAuthentication = new UserAuthentication(command.username(), command.password());
            user.setUserAuthentication(userAuthentication);
            user.setUserData(userData);
            userRepository.save(user);
        }
        return userOptional;
    }
}