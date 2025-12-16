package org.onlinecinema.filmservice.exception;

public class InsufficientPlacesException extends RuntimeException {
    public InsufficientPlacesException(String message) {
        super(message);
    }
}

