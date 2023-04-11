package fr.mobiservice.exception;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long reservationID) {
        super("the reservation " + reservationID + " is not found");
    }
}
