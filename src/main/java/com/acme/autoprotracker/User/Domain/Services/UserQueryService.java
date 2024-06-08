package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllUsersQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle (GetUserByIdQuery query);
    List<User> handle (GetAllUsersQuery query);
}