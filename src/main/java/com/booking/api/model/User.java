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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
    private String userCreationDate;

    public User( String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        userCreationDate = getUserCreationDate(); // Cambio de getUserCreationData a getUserCreationDate
    }

    public User( String idUser,String firstName, String lastName, String email) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        userCreationDate = getUserCreationDate(); // Cambio de getUserCreationData a getUserCreationDate
    }

    public String getUserCreationDate() { // Cambio de getUserCreationData a getUserCreationDate
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return currentDateTime.format(formatter);
    }
}