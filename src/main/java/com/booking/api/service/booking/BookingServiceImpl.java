package com.booking.api.service.booking;

import com.booking.api.exception.BookingNotFoundException;
import com.booking.api.model.Booking;
import com.booking.api.model.User;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingMapper;
import com.booking.api.model.dto.booking.BookingResponseDto;
import com.booking.api.model.dto.user.UserDto;
import com.booking.api.model.dto.user.UserMapper;
import com.booking.api.model.dto.user.UserResponseDto;
import com.booking.api.repository.booking.BookingRepository;
import com.booking.api.repository.user.UserRepository;
import com.booking.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<BookingResponseDto> getAllBookings() {
        List<BookingResponseDto> responseDtoList = new ArrayList<>();
        bookingRepository.getAllBookings().forEach(booking -> responseDtoList.add(BookingMapper.bookingToBookingResponseDto(booking)));
        return responseDtoList;
    }

    @Override
    public Optional<BookingResponseDto> findBookingById(String idBooking) {
        Optional<Booking> searchBooking = bookingRepository.findBookingById(idBooking);
        if(searchBooking.isPresent()){
            Booking booking = searchBooking.get();
            return Optional.of(BookingMapper.bookingToBookingResponseDto(booking));
        }else {
            throw  new BookingNotFoundException("Booking not found with ID: " + idBooking);
        }
    }

    @Override
    public BookingResponseDto saveBooking(BookingDto booking) {
        User user = userRepository.saveUser(booking.getUserData());
        Booking bookingSava = bookingRepository.saveBooking(BookingMapper.bookingDtoToBooking(booking));
        return BookingMapper.bookingToBookingResponseDto(bookingSava);
    }


    @Override
    public BookingResponseDto updateBooking(String idBooking, BookingDto booking) {
            return BookingMapper.bookingToBookingResponseDto(bookingRepository.updateBooking(idBooking,BookingMapper.bookingDtoToBooking(booking)));
    }

    @Override
    public Boolean deleteBookingById(String idBooking) {
        Optional<Booking> findBooking = bookingRepository.findBookingById(idBooking);
        if(findBooking.isPresent()){
            bookingRepository.deleteBookingById(idBooking);
            return true;
        }else {
            return false;
        }
    }
}
