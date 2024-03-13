package com.booking.api.booking;

import com.booking.api.model.Booking;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingResponseDto;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceDto;
import com.booking.api.model.dto.user.UserDto;

import java.time.LocalDate;
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


    public static List<BookingInvoiceDto> loadList(){
        UserDto userDto = new UserDto("John", "Doe", "john.doe@example.com", new ArrayList<>());
        BookingDto bookingDto = new BookingDto(userDto, "CityA", "CityB", "2024-03-13", "01:00", "2 hours", "A1");
        BookingInvoiceDto bookingInvoiceDto = new BookingInvoiceDto(LocalDate.now(), userDto, bookingDto, "ADA TRAVEL");

        // Agregar datos de prueba a la lista
        List<BookingInvoiceDto> bookingInvoiceList = new ArrayList<>();
        bookingInvoiceList.add(bookingInvoiceDto);

        return bookingInvoiceList;
    }

    public static UserDto createUserData(){
        UserDto userDto = new UserDto("Mr robot", "Evil Corp", "fsociety@gmail.com");
        BookingDto bookingDto = new BookingDto(userDto, "CityA", "CityB", "2024-03-13", "01:00", "2 hours", "A1");
        BookingInvoiceDto bookingInvoiceDto = new BookingInvoiceDto(LocalDate.now(), userDto, bookingDto, "ADA TRAVEL");
        userDto.addBookingInvoiceToHistory(bookingInvoiceDto);
        return userDto;
    }

    public static BookingResponseDto createBookingResponseDto() {
        LocalDate fechaEsperada = LocalDate.of(2024, 3, 15);
        LocalTime horaEsperada = LocalTime.of(1, 0);


        return new BookingResponseDto("1", createUserData(), "Bucaramanga", "Bogota", "2024-03-13", "01:00", "6", "18", 30000);
    }


    public static Booking createBooking(){
        LocalDate fechaEsperada = LocalDate.of(2024,3,15);
        String fechaEsperadaToString = fechaEsperada.format(DateTimeFormatter.ISO_DATE);
        LocalTime horaEsperada = LocalTime.of(1,0);
        String horaEsperadToString = horaEsperada.format(DateTimeFormatter.ofPattern("HH:mm"));

        return new Booking("1",createUserData(), "Bucaramanga", "Bogota","2024-03-13", "01:00","6","18",3000);
    }
}
