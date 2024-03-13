package com.booking.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvoiceNotFoundException extends ResponseStatusException {
    public InvoiceNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Invoice with ID: " + id + "nor found in data base.");
    }
}
