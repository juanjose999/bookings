package com.booking.api.service.booking;

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
        List<BookingResponseDto> responseDto = new ArrayList<>();
        bookingRepository.getAllBookings().forEach(booking -> responseDto.add(BookingMapper.bookingToBookingResponseDto(booking)));
        return responseDto;
    }

    @Override
    public Optional<BookingResponseDto> findBookingById(String idBooking) {
        Optional<Booking> findBooking = bookingRepository.findBookingById(idBooking);
        if(findBooking.isPresent()){
            Booking existinBooking = findBooking.get();
            return Optional.of(BookingMapper.bookingToBookingResponseDto(existinBooking));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public BookingResponseDto saveBooking(BookingDto bookingDto) {

        UserDto userDto = bookingDto.getUserData();
        UserResponseDto savedUser = userService.saveUser(userDto);

        Booking booking = bookingRepository.saveBooking(BookingMapper.bookingDtoToBooking(bookingDto));

        BookingInvoiceDto bookingInvoiceDto = new BookingInvoiceDto(
                LocalDate.now(),
                userDto,
                booking,
                "ADA TRAVEL"
        );
        userDto.addBookingInvoiceToHistory(bookingInvoiceDto);
        UserResponseDto updateUser = userService.updateUser(savedUser.getIdUser(), userDto);

        BookingInvoiceResponseDto savedInvoice = bookingInvoiceService.saveBookingInvoice(bookingInvoiceDto);

        return BookingMapper.bookingToBookingResponseDto(booking);
    }

    @Override
    public BookingResponseDto updateBooking(String idBooking, BookingDto bookingDto) {
        return null;
    }

    @Override
    public Boolean deleteBookingById(String idBooking) {
        return null;
    }
}
