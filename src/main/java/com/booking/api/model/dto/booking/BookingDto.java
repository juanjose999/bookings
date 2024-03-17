package com.booking.api.model.dto.booking;

import com.booking.api.model.User;
import com.booking.api.model.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
    private User userData;
    private String originLocation;
    private String destination;
    private String departureTime;
    private String departureHour;
    private double hoursTripDuration;
    private String seatNumber;
}
