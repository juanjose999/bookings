package com.booking.api.service.user;

import com.booking.api.model.User;
import com.booking.api.model.dto.user.UserDto;
import com.booking.api.model.dto.user.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDto> getAllUser();
    Optional<UserResponseDto> findUserById(String idUser);
    UserResponseDto saveUser (UserDto userDto);
    UserResponseDto updateUser (String idUser, UserDto userDto);
    Boolean deleteUserById(String idUser);
}
