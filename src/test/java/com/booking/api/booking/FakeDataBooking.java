package com.booking.api.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FakeDataBooking {

    public static UserDataDto createUserData(){
        return new UserDataDto("Mr robot", "Evil Corp", "fsociety@gmail.com");
    }

    public static BookingResponseDto createBookingResponseDto(){
        LocalDate fechaEsperada = LocalDate.of(2024,3,15);
        String fechaEsperadaToString = fechaEsperada.format(DateTimeFormatter.ISO_DATE);
        LocalTime horaEsperada = LocalTime.of(1,0);
        String horaEsperadaToString = horaEsperada.format(DateTimeFormatter.ofPattern("HH:mm"));

        return new BookingResponseDto("1", createUserData(), "Bucaramanga", "Bogota", fechaEsperadaToString, horaEsperadaToString, "6", "18");
    }

    public static Booking createBooking(){
        LocalDate fechaEsperada = LocalDate.of(2024,3,15);
        String fechaEsperadaToString = fechaEsperada.format(DateTimeFormatter.ISO_DATE);
        LocalTime horaEsperada = LocalTime.of(1,0);
        String horaEsperadToString = horaEsperada.format(DateTimeFormatter.ofPattern("HH:mm"));

        return new Booking("1",createUserData(), "Bucaramanga", "Bogota",fechaEsperadaToString,horaEsperadToString,"6","18");
    }
}
