package com.booking.api.model.dto.booking;

import com.booking.api.model.Booking;

public class BookingMapper {

    public static Booking bookingDtoToBooking(BookingDto bookingDto){
        return new Booking(
                bookingDto.getUserData(),
                bookingDto.getOriginLocation(),
                bookingDto.getDestination(),
                bookingDto.getDepartureTime(),
                bookingDto.getDepartureHour(),
                bookingDto.getDurationTrip(),
                bookingDto.getSeatNumber()
        );
    }

    public static BookingResponseDto bookingToBookingResponseDto(Booking booking){
        return new BookingResponseDto(
                booking.getIdBooking(),
                booking.getUserData(),
                booking.getOriginLocation(),
                booking.getDestination(),
                booking.getDepartureTime(),
                booking.getDepartureHour(),
                booking.getDurationTrip(),
                booking.getSeatNumber(),
                booking.getCostTrip()
        );
    }
}
