package com.booking.api.model.dto.user;

import com.booking.api.model.Booking;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private List<BookingInvoiceDto> bookingHistory;  // Agrega el historial de facturas

    // Otros métodos y constructor



    // Método para agregar una factura al historial y devolver un nuevo UserDto actualizado
    public UserDto addBookingInvoiceToHistory(BookingInvoiceDto bookingInvoiceDto) {
        List<BookingInvoiceDto> updatedHistory = new ArrayList<>(bookingHistory != null ? bookingHistory : Collections.emptyList());
        updatedHistory.add(bookingInvoiceDto);

        return new UserDto(firstName, lastName, email, updatedHistory);
    }
}
