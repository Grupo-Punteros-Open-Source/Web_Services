package com.acme.autoprotracker.maintenance.domain.exceptions;

public class MaintenanceNotFoundException extends RuntimeException{
    public MaintenanceNotFoundException(Long aLong) {
        super("Maintance with id " + aLong + " not found");
    }
}
