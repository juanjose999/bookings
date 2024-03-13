package com.booking.api.model;

import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservationInvoices")
public class BookingInvoice implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String idInvoice;
    private LocalDate dateIssueInvoice;
    private UserDto userDetailsList;
    private BookingDto detailsTrip;
    private String invoiceSellerName = "ADA TRAVEL";

    public BookingInvoice(LocalDate dateIssueInvoice, UserDto userDetailsList, BookingDto detailsTrip, String invoiceSellerName) {
        this.dateIssueInvoice = dateIssueInvoice;
        this.userDetailsList = userDetailsList;
        this.detailsTrip = detailsTrip;
        this.invoiceSellerName = invoiceSellerName;
    }
}
