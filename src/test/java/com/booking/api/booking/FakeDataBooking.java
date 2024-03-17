package com.booking.api.booking;

import com.booking.api.model.Booking;
import com.booking.api.model.User;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingResponseDto;
import com.booking.api.model.dto.user.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class FakeDataBooking {
    public static LocalDate fechaEsperada = LocalDate.of(2024,3,15);
    public static String fechaEsperadaToString = fechaEsperada.format(DateTimeFormatter.ISO_DATE);
    public static LocalTime horaEsperada = LocalTime.of(1,0);
    public static String horaEsperadaToString = horaEsperada.format(DateTimeFormatter.ofPattern("HH:mm"));


    public static BookingDto loadList(){
        User user = new User("1","Mr robot", "Evil Corp", "fsociety@gmail.com");
        BookingDto bookingDto = new BookingDto( "Bucaramanga", "Bogota", "2024-03-15", "01:00", 0, "18",user);
        return bookingDto;
    }

    public static User createUserData(){
        User user = new User("Mr robot", "Evil Corp", "fsociety@gmail.com");
        BookingDto bookingDto = new BookingDto(  "Bucaramanga", "Bogota", "2024-03-13", "01:00", 0, "18",user);
        return user;
    }

    public static BookingResponseDto createBookingResponseDto() {
        LocalDate fechaEsperada = LocalDate.of(2024, 3, 15);
        LocalTime horaEsperada = LocalTime.of(1, 0);

        return new BookingResponseDto("a1", "Bucaramanga", "Bogota", "2024-03-13", "01:00", 4, "18", 30000,createUserData());
    }


    public static Booking createBooking(){
        LocalDate fechaEsperada = LocalDate.of(2024,3,15);
        String fechaEsperadaToString = fechaEsperada.format(DateTimeFormatter.ISO_DATE);
        LocalTime horaEsperada = LocalTime.of(1,0);
        String horaEsperadToString = horaEsperada.format(DateTimeFormatter.ofPattern("HH:mm"));

        return new Booking("1","Bucaramanga", "Bogota","2024-03-13", "01:00",6,"18",createUserData());
    }
}
