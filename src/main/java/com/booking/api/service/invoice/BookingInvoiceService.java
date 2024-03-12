package com.booking.api.service.invoice;

import com.booking.api.model.dto.bookinginvoice.BookingInvoiceDto;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceResponseDto;

import java.util.List;
import java.util.Optional;

public interface BookingInvoiceService {
    List<BookingInvoiceResponseDto> getAllBookingInvoice();
    Optional<BookingInvoiceResponseDto> findBookingInvoiceById(String idBookingInvoice);
    BookingInvoiceResponseDto saveBookingInvoice (BookingInvoiceDto bookingInvoiceDto);
    BookingInvoiceResponseDto updateBookingInvoice (String idBookingInvoice, BookingInvoiceDto bookingInvoiceDto);
    Boolean deleteBookingInvoiceById(String idBookingInvoice);
}
