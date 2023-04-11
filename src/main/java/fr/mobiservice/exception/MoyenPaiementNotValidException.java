package fr.mobiservice.exception;

public class MoyenPaiementNotValidException extends RuntimeException {

    public MoyenPaiementNotValidException(String message) {
        super(message);
    }
}
