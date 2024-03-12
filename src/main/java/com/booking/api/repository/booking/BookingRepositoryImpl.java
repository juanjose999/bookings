package com.booking.api.repository.booking;

import com.booking.api.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class BookingRepositoryImpl implements BookingRepository{
    @Autowired
    private  BookingMongoRepository bookingMongoRepository;


    @Override
    public List<Booking> getAllBookings() {
        return bookingMongoRepository.findAll();
    }

    @Override
    public Optional<Booking> findBookingById(String idBooking) {
        Optional<Booking> findBooking = bookingMongoRepository.findById(idBooking);
        return Optional.ofNullable(findBooking.orElse(null));
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingMongoRepository.save(booking);
    }

    @Override
    public Booking updateBooking(String idBooking, Booking booking) {
        Optional<Booking> findBookingToUpdate = bookingMongoRepository.findById(idBooking);
        if(findBookingToUpdate.isPresent()){
            Booking existingBooking = findBookingToUpdate.get();
            existingBooking.setUserData(booking.getUserData());
            existingBooking.setOriginLocation(booking.getOriginLocation());
            existingBooking.setDestination(booking.getDestination());
            existingBooking.setDepartureTime(booking.getDepartureTime());
            existingBooking.setDepartureHour(booking.getDepartureHour());
            existingBooking.setDurationTrip(booking.getDurationTrip());
            existingBooking.setCostTrip(booking.getCostTrip());
            bookingMongoRepository.save(existingBooking);
            return existingBooking;
        }else {
            throw new BookingNotFoundException("Booking with ID: "  + idBooking + " not found");
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
