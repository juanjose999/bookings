package com.booking.api.model.dto.user;

import com.booking.api.model.User;

public class UserMapper {

    public static User userDtoToUser(UserDto userDto){
        return new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }

    public static UserResponseDto userToUserResponseDto(User user){
        return new UserResponseDto(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

}
