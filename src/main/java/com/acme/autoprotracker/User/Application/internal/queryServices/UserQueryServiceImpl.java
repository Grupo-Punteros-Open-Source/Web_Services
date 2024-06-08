package com.acme.autoprotracker.User.Application.internal.queryServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllUsersQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetUserByIdQuery;
import com.acme.autoprotracker.User.Domain.Services.UserQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
}