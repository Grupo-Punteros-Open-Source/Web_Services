package com.acme.autoprotracker.maintenance.domain.exceptions;

public class DetailNotFoundException extends RuntimeException {
    public DetailNotFoundException(Long aLong) {
        super("Detail with id " + aLong + " not found");
    }
}
