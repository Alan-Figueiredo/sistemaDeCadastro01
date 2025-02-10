package org.example.exception;

public class NomeInvalidoException extends UserException {
    public NomeInvalidoException(String message) {
        super("O nome deve ter pelo menos 10 caracteres.");
    }
}
