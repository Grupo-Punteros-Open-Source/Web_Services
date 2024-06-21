package com.acme.autoprotracker.iam.domain.services;

import com.acme.autoprotracker.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}