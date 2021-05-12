package ru.nashev.try2.service.impl;

import ru.nashev.try2.service.UserError;

@UserError
public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String message) {
        super(message);
    }

}
