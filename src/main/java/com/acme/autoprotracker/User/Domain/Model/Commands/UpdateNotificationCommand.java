package com.acme.autoprotracker.User.Domain.Model.Commands;

import java.time.ZonedDateTime;

public record UpdateNotificationCommand(Long id, String type, String title, String message, String timestamp, Boolean read) {
}