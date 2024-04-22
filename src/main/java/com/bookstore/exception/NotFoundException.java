package com.bookstore.exception;

import com.bookstore.exception.base.BaseNotFoundException;

public class NotFoundException extends BaseNotFoundException {

    public NotFoundException(Long id) {
        super("Object with id " + id + " not found.");
    }
}
