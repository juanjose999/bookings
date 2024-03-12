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

    @GetMapping("/{idBooking}")
    public ResponseEntity<BookingResponseDto> finBookingById(@PathVariable String idBooking){
        try{
            Optional<BookingResponseDto> searchBooking = bookingService.findBookingById(idBooking);
            if(searchBooking.isPresent()){
                return new ResponseEntity(searchBooking,HttpStatus.OK);
            }else {
                return new ResponseEntity("Booking not found with ID: " + idBooking, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity("Booking not found with ID: " + idBooking, HttpStatus.NOT_FOUND);
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
        try{
            Optional<BookingResponseDto> updateBooking = bookingService.findBookingById(idBooking);
            if(updateBooking.isPresent()){
                BookingResponseDto bookingResponseDto = bookingService.updateBooking(idBooking, bookingDto);
                return new ResponseEntity<>(bookingResponseDto,HttpStatus.OK);
            }else {
                return new ResponseEntity("The booking is not found", HttpStatus.NOT_FOUND);
            }
        }catch (BookingNotFoundException e){
            return new ResponseEntity(idBooking, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idBooking}")
    public ResponseEntity<Boolean> deleteBookingById(@PathVariable String idBooking){
        try{
            Boolean isDeleteBooking = bookingService.deleteBookingById(idBooking);
            if(isDeleteBooking){
                return new ResponseEntity("The booking is detele.",HttpStatus.OK);
            }else {
                return new ResponseEntity("Error in delete booking.", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity("Error in delete booking." + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
