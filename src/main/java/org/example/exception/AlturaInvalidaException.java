package org.example.exception;

public class AlturaInvalidaException extends UserException {

    public AlturaInvalidaException(String message) {
        super("O formato da altura é inválido. Utilize vírgulas em vez de pontos, ex: 1,75.");
    }
}
