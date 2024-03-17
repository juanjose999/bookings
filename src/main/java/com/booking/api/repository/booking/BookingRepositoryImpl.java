package com.booking.api.repository.booking;

import com.booking.api.exception.BookingNotFoundException;
import com.booking.api.model.Booking;
import com.booking.api.model.User;
import com.booking.api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class BookingRepositoryImpl implements BookingRepository{
    @Autowired
    private  BookingMongoRepository bookingMongoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingMongoRepository.findAll();
    }

    @Override
    public Optional<Booking> findBookingById(String idBooking) {
        return bookingMongoRepository.findById(idBooking);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking entity must not be null");
        }
        User user = userRepository.saveUser(booking.getUserData());
        booking.setUserData(user);
        Booking saveBooking = bookingMongoRepository.save(booking);
        return bookingMongoRepository.save(booking);
    }

    @Override
    public Booking updateBooking(String idBooking, Booking booking) {

        Optional<Booking> findBookingToUpdate = bookingMongoRepository.findById(idBooking);

        if(findBookingToUpdate.isPresent()){
            Booking existingBooking = findBookingToUpdate.get();
            existingBooking.setOriginLocation(booking.getOriginLocation());
            existingBooking.setDestination(booking.getDestination());
            existingBooking.setDepartureTime(booking.getDepartureTime());
            existingBooking.setDepartureHour(booking.getDepartureHour());
            existingBooking.setHoursTripDuration(booking.getHoursTripDuration());

            User user = existingBooking.getUserData();
            user.setFirstName(booking.getUserData().getFirstName());
            user.setLastName(booking.getUserData().getLastName());
            user.setEmail(booking.getUserData().getEmail());
            userRepository.saveUser(user);
             return bookingMongoRepository.save(existingBooking);
        } else {
            throw new BookingNotFoundException("Booking with ID: " + idBooking + " not found");
        }
    }

    @Override
    public Boolean deleteBookingById(String idBooking) {
        Optional<Booking> findBookingToDelete = bookingMongoRepository.findById(idBooking);
        if (findBookingToDelete.isPresent()){
            bookingMongoRepository.deleteById(idBooking);
            return true;
        }
        return false;
    }
}
