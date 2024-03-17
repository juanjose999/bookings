package com.booking.api.repository.user;

import com.booking.api.exception.UserNotFoundException;
import com.booking.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public List<User> getAllUser() {
        return userMongoRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(String idUser) {
        Optional<User> findUser = userMongoRepository.findById(idUser);
        return Optional.ofNullable(findUser.orElseThrow(()-> new UserNotFoundException(idUser)));
    }

    @Override
    public User saveUser(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public User updateUser(String idUser, User user) {
        Optional<User> findUser = userMongoRepository.findById(idUser);
        if (findUser.isPresent()) {
            User existingUser = findUser.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            // No es necesario crear un nuevo objeto, solo guardar el usuario existente actualizado
            return userMongoRepository.save(existingUser);
        } else {
            throw new UserNotFoundException(idUser);
        }
    }


    @Override
    public Boolean deleteUserById(String idUser) {
        Optional<User> findUser = userMongoRepository.findById(idUser);
        if(findUser.isPresent()){
            userMongoRepository.deleteById(idUser);
            return true;
        }else {
            return false;
        }
    }
}
