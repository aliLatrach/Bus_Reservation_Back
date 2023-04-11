package fr.mobiservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(value = { NombrePlaceInsuffisantException.class, ReservationNotFoundException.class, MoyenPaiementNotValidException.class, 
        ClientNotFoundException.class, IllegalArgumentException.class, TrajetNotFoundException.class })
    public ResponseEntity<String> handleResourceNotFoundException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}