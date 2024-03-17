package com.booking.api.model;

import com.booking.api.model.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Data
@Document(collection = "bookings")
public class Booking implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String idBooking;
    private String bookingCreationDate;
    private String originLocation;
    private String destination;
    private String departureTime;
    private String departureHour;
    private double hoursTripDuration;
    private String seatNumber;
    private double costTrip;
    @DBRef
    private User userData;
    private String bookingSeller;

    public Booking(String idBooking ,String originLocation, String destination, String departureTime, String departureHour, double hoursTripDuration, String seatNumber, User userData) {
        this.idBooking = idBooking;
        bookingCreationDate = getBookingCreationDate();
        this.bookingSeller = "ADA TRAVEL TECHNOLOGY";
        this.originLocation = originLocation;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureHour = departureHour;
        this.hoursTripDuration = hoursTripDuration;
        this.seatNumber = seatNumber;
        this.userData = userData;
        this.costTrip = getCostTrip();
    }

    public Booking(String originLocation, String destination, String departureTime, String departureHour, double hoursTripDuration, String seatNumber, User userData) {
        bookingCreationDate = getBookingCreationDate();
        this.bookingSeller = "ADA TRAVEL TECHNOLOGY";
        this.originLocation = originLocation;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureHour = departureHour;
        this.hoursTripDuration = hoursTripDuration;
        this.seatNumber = seatNumber;
        this.userData = userData;
        this.costTrip = getCostTrip();
    }

    public double getCostTrip(){
        if(hoursTripDuration>0){
            double hoursTrip = hoursTripDuration;
            double costHour = 200000;
            return costHour*hoursTrip;
        }else {
            throw new RuntimeException("duration hours void, please input data in duration.");
        }
    }

    public String getBookingCreationDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return currentDateTime.format(formatter);
    }

}
