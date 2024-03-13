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
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
@Document(collection = "bookings")
public class Booking implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String idBooking;
    @DBRef
    private UserDto userData;
    private String originLocation;
    private String destination;
    private String departureTime;
    private String departureHour;
    private String durationTrip;
    private String seatNumber;
    private double costTrip;

    public Booking(UserDto userData, String originLocation, String destination, String departureTime, String departureHour, String durationTrip, String seatNumber) {
        this.userData = userData;
        this.originLocation = originLocation;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureHour = departureHour;
        this.durationTrip = durationTrip;
        this.seatNumber = seatNumber;
        updateCostTrip();
    }


    public void updateCostTrip(){
        if(durationTrip != null){
            int hoursTrip = Integer.parseInt(durationTrip);
            double costHour = 20.000;
            double costTotal = costHour*hoursTrip;
            this.setCostTrip(costTotal);
        }
    }
}
