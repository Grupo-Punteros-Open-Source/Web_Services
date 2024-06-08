package com.acme.autoprotracker.workshop.domain.exceptions;

public class AdvertisingNotFoundException extends RuntimeException {
    public AdvertisingNotFoundException(Long aLong) {
        super("Advertising with id " + aLong + " not found");
    }
}
