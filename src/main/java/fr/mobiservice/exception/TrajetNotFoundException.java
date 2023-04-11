package fr.mobiservice.exception;

public class TrajetNotFoundException extends RuntimeException {

    public TrajetNotFoundException() {
        super("this trip is not found");
    }

}
