package com.booking.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BookingNotFoundException extends ResponseStatusException {
    public BookingNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Booking with ID: " + id + " not found");
    }
}
