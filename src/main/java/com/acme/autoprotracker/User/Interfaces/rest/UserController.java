package com.acme.autoprotracker.User.Interfaces.rest;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Services.UserCommandService;
import com.acme.autoprotracker.User.Domain.Services.UserQueryService;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateUserResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateUserResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UserResource;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.CreateUserCommandFromResourceAssembler;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.UpdateUserCommandFromResourceAssembler;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final CreateUserCommandFromResourceAssembler createUserCommandFromResourceAssembler;
    private final UpdateUserCommandFromResourceAssembler updateUserCommandFromResourceAssembler;
    private final UserResourceFromEntityAssembler userResourceFromEntityAssembler;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService,
                          CreateUserCommandFromResourceAssembler createUserCommandFromResourceAssembler,
                          UpdateUserCommandFromResourceAssembler updateUserCommandFromResourceAssembler,
                          UserResourceFromEntityAssembler userResourceFromEntityAssembler) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.createUserCommandFromResourceAssembler = createUserCommandFromResourceAssembler;
        this.updateUserCommandFromResourceAssembler = updateUserCommandFromResourceAssembler;
        this.userResourceFromEntityAssembler = userResourceFromEntityAssembler;
    }



    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource createUserResource) {
        var createUserCommand = createUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var userId = userCommandService.handle(createUserCommand);
        var user = userQueryService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resource = userResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.created(null).body(resource);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResource> updateUser(@RequestBody UpdateUserResource updateUserResource, @PathVariable Long userId) {
        var updateUserCommand = updateUserCommandFromResourceAssembler.toCommandFromResource(updateUserResource);
        var user = userCommandService.handle(updateUserCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resource = userResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(resource);
    }



    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.getAllUsers();
        var resources = users.stream().map(userResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var user = userQueryService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = userResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(resource);
    }
}