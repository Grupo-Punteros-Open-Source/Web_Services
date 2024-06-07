package com.acme.autoprotracker.maintenance.domain.model.commands;

public record CreateDetailCommand(String description, Long amount, Long maintanceId) {
}
