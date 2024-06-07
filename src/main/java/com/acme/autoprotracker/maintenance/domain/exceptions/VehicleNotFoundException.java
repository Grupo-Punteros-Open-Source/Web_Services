package com.acme.autoprotracker.maintenance.domain.exceptions;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(Long aLong) {
        super("Vehicle with id " + aLong + " not found");
    }
}
