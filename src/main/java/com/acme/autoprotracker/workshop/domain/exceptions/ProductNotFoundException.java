package com.acme.autoprotracker.workshop.domain.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long aLong) {
        super("Product with id " + aLong + " not found");
    }

}
