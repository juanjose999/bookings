package com.booking.api.repository.user;

import com.booking.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
        return Optional.ofNullable(findUser.orElse(null));
    }

    @Override
    public User saveUser(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public User updateUser(String idUser, User user) {
        Optional<User> findUser = userMongoRepository.findById(idUser);
        if(findUser.isPresent()){
            User existingUser = findUser.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setBookingHistory(user.getBookingHistory());
            userMongoRepository.save(existingUser);
            return existingUser;
        }else {
            throw new UserNotFoundException("Booking with ID: "  + idUser + " not found");
        }
    }

    @Override
    public Boolean deleteUserById(String idUser) {
        User findUser = userMongoRepository.findById(idUser).get();
        if(findUser != null){
            userMongoRepository.deleteById(idUser);
            return true;
        }
        return false;
    }
}
