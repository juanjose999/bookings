package com.booking.api.model.dto.booking;

import com.booking.api.model.Booking;
import com.booking.api.model.User;
import com.booking.api.model.dto.user.UserDto;
import com.booking.api.model.dto.user.UserMapper;

import java.util.List;

public class BookingMapper {

    public static Booking bookingDtoToBooking(BookingDto bookingDto){
        return new Booking(
                bookingDto.getOriginLocation(),
                bookingDto.getDestination(),
                bookingDto.getDepartureTime(),
                bookingDto.getDepartureHour(),
                bookingDto.getHoursTripDuration(),
                bookingDto.getSeatNumber(),
                bookingDto.getUserData()
                );
    }

    public static BookingResponseDto bookingToBookingResponseDto(Booking booking){
        return new BookingResponseDto(
                booking.getIdBooking(),
                booking.getOriginLocation(),
                booking.getDestination(),
                booking.getDepartureTime(),
                booking.getDepartureHour(),
                booking.getHoursTripDuration(),
                booking.getSeatNumber(),
                booking.getCostTrip(),
                booking.getUserData()
                );
    }

}
