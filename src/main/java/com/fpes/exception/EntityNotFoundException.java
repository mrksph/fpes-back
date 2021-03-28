package com.fpes.exception;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message) {
        super("Entity Not Found Exception: " + message);
    }

    public EntityNotFoundException(Long id) {
        super("Entity Not Found Exception: " + id);
    }
}
