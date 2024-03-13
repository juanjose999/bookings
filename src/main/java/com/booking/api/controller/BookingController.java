package com.booking.api.controller;

import com.booking.api.exception.BookingNotFoundException;
import com.booking.api.model.Booking;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingResponseDto;
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
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
        try{
            List<BookingResponseDto> bookingResponseDto = bookingService.getAllBookings();
            if(!bookingResponseDto.isEmpty()){
                return new ResponseEntity<>(bookingResponseDto,HttpStatus.OK);
            }else {
                return new ResponseEntity("Bookings list is empty.", HttpStatus.OK);
            }
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
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<BookingResponseDto> saveBooking(@RequestBody BookingDto bookingDto){
        try{
            BookingResponseDto bookingResponseDto = bookingService.saveBooking(bookingDto);
            return new ResponseEntity<>(bookingResponseDto,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Error in saving booking: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{idBooking}")
    public ResponseEntity<BookingResponseDto> updateBooking(@PathVariable String idBooking, @RequestBody BookingDto bookingDto){
        try {
            BookingResponseDto booking = bookingService.findBookingById(idBooking)
                    .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + idBooking));

            return new ResponseEntity<>(booking, HttpStatus.OK);
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
