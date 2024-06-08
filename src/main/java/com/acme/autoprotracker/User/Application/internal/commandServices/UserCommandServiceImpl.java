package com.acme.autoprotracker.User.Application.internal.commandServices;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateUserCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateUserCommand;
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

        User user = new User(command);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }
        return user.getId();
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        var result = userRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("User does not exist");
        var userToUpdate = result.get();
        try {
            var updatedUser = userRepository.save(userToUpdate.update(
                    command.name(),
                    command.address(),
                    command.phone(),
                    command.email(),
                    command.imageUrl(),
                    command.username(),
                    command.password()
            ));
            return Optional.of(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());
        }
    }
}