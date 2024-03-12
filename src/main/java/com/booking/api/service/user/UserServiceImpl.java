package com.booking.api.service.user;

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
        List<UserResponseDto> userResponseDto = new ArrayList<>();
        userRepository.getAllUser().forEach(user -> userResponseDto.add(UserMapper.userToUserResponseDto(user)));
        return userResponseDto;
    }

    @Override
    public Optional<UserResponseDto> findUserById(String idUser) {
        Optional<User> optionalUser = userRepository.findUserById(idUser);
        return optionalUser.map(UserMapper::userToUserResponseDto);
    }

    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        return UserMapper.userToUserResponseDto(UserMapper.userDtoToUser(userDto));
    }

    @Override
    public UserResponseDto updateUser(String idUser, UserDto userDto) {
        User existingUser = userRepository.updateUser(idUser, UserMapper.userDtoToUser(userDto));
        return UserMapper.userToUserResponseDto(existingUser);
    }

    @Override
    public Boolean deleteUserById(String idUser) {
        return userRepository.deleteUserById(idUser);
    }
}
