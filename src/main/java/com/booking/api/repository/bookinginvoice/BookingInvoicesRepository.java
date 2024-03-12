package com.booking.api.repository.bookinginvoice;

import com.booking.api.model.BookingInvoice;

import java.util.List;

public interface BookingInvoicesRepository {
    List<BookingInvoice> getAllBookingInvoice();
    BookingInvoice findBookingInvoiceById(String idBookingInvoice);
    BookingInvoice saveBookingInvoice (BookingInvoice bookingInvoice);
    BookingInvoice updateBookingInvoice (String idBookingInvoice, BookingInvoice bookingInvoice);
    void deleteBookingInvoiceById(String idBookingInvoice);
}
