package com.booking.api.booking;


import com.booking.api.controller.BookingController;
import com.booking.api.exception.BookingNotFoundException;
import com.booking.api.exception.InvoiceNotFoundException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.booking.api.model.Booking;
import com.booking.api.model.dto.booking.BookingDto;
import com.booking.api.model.dto.booking.BookingMapper;

import com.booking.api.service.booking.BookingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class BookingTest {

    final String BASE_URL = "/v1/bookings";
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private BookingServiceImpl bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(bookingController).build();
    }

    @Test
    public void testFindBookingExisting() throws Exception {

        Booking booking = FakeDataBooking.createBooking();
        when(bookingService.findBookingById("1")).thenReturn(Optional.of(BookingMapper.bookingToBookingResponseDto(booking)));

        mockMvc.perform(get(BASE_URL +"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idBooking", is("1")))
                .andExpect(jsonPath("$.originLocation", is("Bucaramanga")))
                .andExpect(jsonPath("$.destination", is("Bogota")))
                .andExpect(jsonPath("$.departureTime").exists()) // You may need to adjust this based on the actual structure
                .andExpect(jsonPath("$.departureHour").exists()) // You may need to adjust this based on the actual structure
                .andExpect(jsonPath("$.hoursTripDuration", is(6.0)))
                .andExpect(jsonPath("$.seatNumber", is("18")))
                .andExpect(jsonPath("$.userData.lastName", is("Evil Corp")))
                .andExpect(jsonPath("$.userData.email", is("fsociety@gmail.com")))
                .andExpect(jsonPath("$.userData.firstName", is("Mr robot")));


        verify(bookingService, times(1)).findBookingById("1");
    }


    @Test
    public void testFindBookingByIdNotExisting() throws Exception {
        String nonExistingBookingId = "420";

        mockMvc.perform(get(BASE_URL + "/" + nonExistingBookingId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value(nonExistingBookingId));

        verify(bookingService, times(1)).findBookingById(nonExistingBookingId);
    }

    @Test
    public void testSaveNewBooking() throws Exception{
        Booking booking= FakeDataBooking.createBooking();

        when(bookingService.saveBooking(any())).thenReturn(BookingMapper.bookingToBookingResponseDto(booking));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(booking);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(bookingService, times(1)).saveBooking(any());
    }


    @Test
    public void testDeleteExistingBooking() throws Exception {
        Booking booking = FakeDataBooking.createBooking();
        when(bookingService.deleteBookingById("1")).thenReturn(true);
        String json = "{\"idBooking\":\"1\",\"userData\":{\"firstName\":\"Mr robot\",\"lastName\":\"Evil Corp\",\"email\":\"fsociety@gmail.com\"},\"originLocation\":\"Bucaramanga\",\"destination\":\"Bogota\",\"departureTime\":\"2024-03-15\",\"departureHour\":\"01:00\",\"durationTrip\":\"6\",\"seatNumber\":\"18\",\"costTrip\":3000.0}";

        mockMvc.perform(delete(BASE_URL +  "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).deleteBookingById("1");
    }

    @Test
    public void testDeleteBookingNotExisting() throws Exception {
        String id = "2";
        when(bookingService.findBookingById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andExpect(status().isNotFound());
        verify(bookingService, times(1)).deleteBookingById(id);
    }

}
