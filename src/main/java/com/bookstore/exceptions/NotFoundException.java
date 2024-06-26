package com.bookstore.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Object with id " + id + " not found.");
    }
}
