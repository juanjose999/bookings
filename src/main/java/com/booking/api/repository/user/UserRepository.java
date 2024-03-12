package com.booking.api.repository.user;

import com.booking.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUser();
    Optional<User> findUserById(String idUser);
    User saveUser (User user);
    User updateUser (String idUser, User user);
    Boolean deleteUserById(String idUser);
}
