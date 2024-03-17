package com.booking.api.controller;

import com.booking.api.exception.BookingNotFoundException;
import com.booking.api.model.Booking;
import com.booking.api.model.User;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingResponseDto;
import com.booking.api.model.dto.user.UserDto;
import com.booking.api.model.dto.user.UserMapper;
import com.booking.api.repository.user.UserRepository;
import com.booking.api.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bookings")
@CrossOrigin(origins = "http://localhost:8082")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
        try{
            return ResponseEntity.ok(bookingService.getAllBookings());
        }catch (Exception e){
            return new ResponseEntity("Error in getAllBookings controller: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findBookingById(@PathVariable String id) {
        try {
            BookingResponseDto booking = bookingService.findBookingById(id)
                    .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));

            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (BookingNotFoundException ex) {
            return new ResponseEntity("Booking with ID: " +id+ " not found in data base.", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<BookingResponseDto> saveBooking(@RequestBody BookingDto booking){
        try{
            BookingResponseDto response = bookingService.saveBooking(booking);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity("Error in saving booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{idBooking}")
    public ResponseEntity<BookingResponseDto> updateBooking(@PathVariable String idBooking, @RequestBody BookingDto booking){
        try {
            BookingResponseDto bookingResponseDto = bookingService.updateBooking(idBooking, booking);
            return ResponseEntity.status(HttpStatus.OK).body(bookingResponseDto);
        } catch (BookingNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idBooking}")
    public ResponseEntity<Boolean> deleteBookingById(@PathVariable String idBooking){
        Boolean deleted = bookingService.deleteBookingById(idBooking);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
