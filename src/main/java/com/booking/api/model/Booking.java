package com.booking.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "bookings")
public class Booking implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String idBooking;
    @DBRef
    private User userData;
    private String originLocation;
    private String destination;
    private LocalDate departureTime;
    private LocalDate departureHour;
    private String durationTrip;
    private String seatNumber;
}
