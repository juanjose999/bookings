package com.booking.api.model.dto.user;

import com.booking.api.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String idUser;
    private String firstName;
    private String lastName;
    private String email;
}
