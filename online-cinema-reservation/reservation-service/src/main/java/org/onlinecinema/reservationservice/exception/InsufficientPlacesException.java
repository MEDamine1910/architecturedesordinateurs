package org.onlinecinema.reservationservice.exception;

public class InsufficientPlacesException extends RuntimeException {
    public InsufficientPlacesException(String message) {
        super(message);
    }
}

