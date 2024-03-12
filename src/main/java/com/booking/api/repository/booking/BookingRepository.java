package com.booking.api.repository.booking;

import com.booking.api.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<Booking> getAllBookings();
    Optional<Booking> findBookingById(String idBooking);
    Booking saveBooking(Booking booking);
    Booking updateBooking(String idBooking,Booking booking);
    Boolean deleteBookingById(String idBooking);

}
