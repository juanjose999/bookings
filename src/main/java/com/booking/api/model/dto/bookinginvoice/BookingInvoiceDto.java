package com.booking.api.model.dto.bookinginvoice;

import com.booking.api.model.Booking;
import com.booking.api.model.User;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInvoiceDto {
    private LocalDate dateIssueInvoice;
    private UserDto userDetailsList;
    private BookingDto detailsTrip;
    private String invoiceSellerName = "ADA TRAVEL";
}
