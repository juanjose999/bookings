package com.booking.api.service.user;

import com.booking.api.exception.UserNotFoundException;
import com.booking.api.model.User;
import com.booking.api.model.dto.user.UserDto;
import com.booking.api.model.dto.user.UserMapper;
import com.booking.api.model.dto.user.UserResponseDto;
import com.booking.api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUser() {
        List<UserResponseDto> userResponse = new ArrayList<>();
        userRepository.getAllUser().forEach(user-> userResponse.add(UserMapper.userToUserResponseDto(user)));
        return userResponse;
    }

    @Override
    public Optional<UserResponseDto> findUserById(String idUser) {
        Optional<User> optionalUser = userRepository.findUserById(idUser);
        if(optionalUser.isPresent()){
            User userExisting = optionalUser.get();
            return Optional.of(UserMapper.userToUserResponseDto(userExisting));
        }
        else {
            throw new UserNotFoundException(idUser);
        }
    }

    @Override
    public UserResponseDto saveUser(UserDto user) {
        return UserMapper.userToUserResponseDto(userRepository.saveUser(UserMapper.userDtoToUser(user)));
    }

    @Override
    public UserResponseDto updateUser(String idUser, UserDto user) {
        User updateUser = userRepository.updateUser(idUser, UserMapper.userDtoToUser(user));
        return UserMapper.userToUserResponseDto(updateUser);
    }

    @Override
    public Boolean deleteUserById(String idUser) {
        return userRepository.deleteUserById(idUser);
    }
}
