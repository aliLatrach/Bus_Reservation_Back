package fr.mobiservice.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long clientId) {
        super("The customer with the id " + clientId + "is not found");
    }
}
