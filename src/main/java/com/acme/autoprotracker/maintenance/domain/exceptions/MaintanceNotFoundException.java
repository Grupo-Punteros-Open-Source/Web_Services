package com.acme.autoprotracker.maintenance.domain.exceptions;

public class MaintanceNotFoundException extends RuntimeException{
    public MaintanceNotFoundException(Long aLong) {
        super("Maintance with id " + aLong + " not found");
    }
}
