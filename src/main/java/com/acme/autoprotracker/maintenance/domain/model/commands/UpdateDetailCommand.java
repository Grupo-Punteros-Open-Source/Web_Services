package com.acme.autoprotracker.maintenance.domain.model.commands;

public record UpdateDetailCommand(Long id,String description, Long amount, Long maintanceId) {
}
