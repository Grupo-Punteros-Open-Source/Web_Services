package com.acme.autoprotracker.iam.domain.model.queries;
import com.acme.autoprotracker.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
