package com.booking.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
public class User implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String idUser;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createUser;
    @DBRef
    private List<Booking> bookingHistory;
}
