package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record CreateNotificationResource(Long user_id, String type, String title, String message, String timestamp, Boolean read) {
}
