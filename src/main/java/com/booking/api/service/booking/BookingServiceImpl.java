package com.booking.api.service.booking;

import com.booking.api.exception.BookingNotFoundException;
import com.booking.api.model.Booking;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingMapper;
import com.booking.api.model.dto.booking.BookingResponseDto;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceDto;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceResponseDto;
import com.booking.api.model.dto.user.UserDto;
import com.booking.api.model.dto.user.UserMapper;
import com.booking.api.model.dto.user.UserResponseDto;
import com.booking.api.repository.booking.BookingRepository;
import com.booking.api.service.invoice.BookingInvoiceService;
import com.booking.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private BookingInvoiceService bookingInvoiceService;
    @Override
    public List<BookingResponseDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.getAllBookings();
        return bookings.stream()
                .map(BookingMapper::bookingToBookingResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingResponseDto> findBookingById(String idBooking) {
        Optional<Booking> searchBooking = bookingRepository.findBookingById(idBooking);
        if(searchBooking.isPresent()){
            return Optional.of(BookingMapper.bookingToBookingResponseDto(searchBooking.get()));
        }else {
            throw  new BookingNotFoundException("Booking not found with ID: " + idBooking);
        }
    }

    @Override
    public BookingResponseDto saveBooking(BookingDto bookingDto) {

        UserDto userDto = bookingDto.getUserData();
        UserResponseDto savedUser = userService.saveUser(userDto);

        Booking bookingDto1 = bookingRepository.saveBooking(BookingMapper.bookingDtoToBooking(bookingDto));

        BookingInvoiceDto bookingInvoiceDto = new BookingInvoiceDto(
                LocalDate.now(),
                userDto,
                bookingDto,
                "ADA TRAVEL"
        );
        userDto.addBookingInvoiceToHistory(bookingInvoiceDto);
        UserResponseDto updateUser = userService.updateUser(savedUser.getIdUser(), userDto);

        BookingInvoiceResponseDto savedInvoice = bookingInvoiceService.saveBookingInvoice(bookingInvoiceDto);

        return BookingMapper.bookingToBookingResponseDto(BookingMapper.bookingDtoToBooking(bookingDto));
    }

    @Override
    public BookingResponseDto updateBooking(String idBooking, BookingDto bookingDto) {
            Optional<Booking> booking = bookingRepository.findBookingById(idBooking);
            if(booking.isPresent()){
                Booking updateBooking = bookingRepository.updateBooking(idBooking, BookingMapper.bookingDtoToBooking(bookingDto));
                return BookingMapper.bookingToBookingResponseDto(updateBooking);
            }else {
                throw new BookingNotFoundException(idBooking);
            }
    }

    @Override
    public Boolean deleteBookingById(String idBooking) {
        return bookingRepository.deleteBookingById(idBooking);
    }
}
