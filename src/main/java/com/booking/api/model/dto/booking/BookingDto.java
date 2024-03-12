package com.booking.api.model.dto.booking;

import com.booking.api.model.User;
import com.booking.api.model.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
    private UserDto userData;
    private String originLocation;
    private String destination;
    private LocalDate departureTime;
    private LocalDate departureHour;
    private String durationTrip;
    private String seatNumber;
}
