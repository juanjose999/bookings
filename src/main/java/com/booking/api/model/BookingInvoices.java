package com.booking.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservationInvoices")
public class BookingInvoices implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String idInvoice;
    private LocalDate invoicesIssueDate;
    private List<User> userDetailsList;
    private List<Booking> detailsTrip;
}
