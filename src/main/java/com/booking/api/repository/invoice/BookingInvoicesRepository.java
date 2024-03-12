package com.booking.api.repository.invoice;

import com.booking.api.model.BookingInvoices;
import com.booking.api.model.User;

import java.util.List;
import java.util.Optional;

public interface BookingInvoicesRepository {
    List<BookingInvoices> getAllBookingInvoice();
    Optional<BookingInvoices> findUserById(String idBookingInvoice);
    BookingInvoices saveUser (BookingInvoices bookingInvoices);
    BookingInvoices updateUser (String idBookingInvoice, BookingInvoices bookingInvoices);
    Boolean deleteBookingById(String idBookingInvoice);
}
