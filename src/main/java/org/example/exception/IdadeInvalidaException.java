package org.example.exception;

public class IdadeInvalidaException extends UserException {

    public IdadeInvalidaException(String message) {
        super("O usuário deve ter pelo menos 18 anos.");
    }
}
