package com.acme.autoprotracker.maintenance.domain.exceptions;

public class HistoryNotFoundException extends RuntimeException{
    public HistoryNotFoundException(Long aLong) {
        super("History with id " + aLong + " not found");
    }
}
