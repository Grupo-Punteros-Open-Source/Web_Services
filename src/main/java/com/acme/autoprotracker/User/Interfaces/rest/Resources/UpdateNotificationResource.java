package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record UpdateNotificationResource(String type, String title, String message, String timestamp, Boolean read) {
}
