package com.booking.api.service.booking;

import com.booking.api.model.Booking;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingResponseDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<BookingResponseDto> getAllBookings();
    Optional<BookingResponseDto> findBookingById(String idBooking);
    BookingResponseDto saveBooking(BookingDto bookinDto);
    BookingResponseDto updateBooking(String idBooking , BookingDto bookingDto);
    Boolean deleteBookingById(String idBooking);
}
