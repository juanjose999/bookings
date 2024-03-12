package com.booking.api.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingTest {

    @Test
    public void testFindBookingExisting() throws Exception {

        Booking booking = FakeDataBooking.createBookingResponseDto();
        when(bookingService.findBookingById("1")).thenReturn(Optional.of(booking));

        mockMvc.perform(get(BASE_URL + "1"))
                .andExpect(status().isOk))
                .andExpect(jsonPath("$id", is("1")))
                .andExpect(jsonPath("$userData.firstName", is("Mr robot")))
                .andExpect(jsonPath("$userData.lastName", is("Evil Corp")))
                .andExpect(jsonPath("$userData.email", is("fsocietye@gmail.com")))
                .andExpect(jsonPath("$originLocation", is("Bucaramanga")))
                .andExpect(jsonPath("$destination", is("Bogota")))
                .andExpect(jsonPath("$departureDate", is(fechaEsperadaString)))
                .andExpect(jsonPath("$departureHour", is(horaEsperadaString)))
                .andExpect(jsonPath("$durationTrip", is("6")))
                .andExpect(jsonPath("$seatNumber", is("18")));

        verify(bookingService, times(1)).findById("1");
    }

    @Test
    public void testFindBookingByIdNotExisting() throws Exception{
        String idBookingSearch = "420";
        when(bookingService.findBookingById(idBookingSearch)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_URL + idBookingSearch))
                .andExpect(status().isNotFound())
                .andExpect(result-> assertTrue(result.getResolveException() instanceof BookingNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"product with ID: " + idBookingSearch + " not found\"", result.getResolvedException().getMessage()));
        verify(bookingService, times(1)).findBookingById(idBooking);

    }

    @Test
    public void testSaveNewBooking() throws Exception{
        Booking booking= FakeDataBooking.createBooking();

        when(bookingService.saveBooking(any())).thenReturn(Booking);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(booking);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .contect(json))
                .andExpect(status().isCreated());

        verify(bookingService, times(1))saveBooking(any());
    }

    @Test
    public void testUpdateExistingBooking() throws Exception{

        Booking booking = FakeDataBooking.createBooking();
        when(bookingService.findBookingById("1")).thenReturn(Optional.of(booking));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(booking);

        mockMvc.perform(put(BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).saveBooking(booking);

    }

    @Test
    public void testUpdateBookingNotExisting() throws Exception{
        String id = "420";
        when(bookingService.findBookingById(id)).thenReturn(Optional.empty());
        Booking booking = FakeDataBooking.createBooking();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(booking);

        mockMvc.perform(put(BASE_URL + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result-> assertTrue(result.getResolvedException() instanceof BookingNotFoundException))
                .andExpect(resul-> assertEquals("404 NOT FOUNT \ with ID: " + id + " not found\"", resul.getResolvedException().getMessage()));

        verify(productService, times(0)).saveBooking(any());
    }

    @Test
    public void testDeleteExistinBooking() throws Exception{
        Boooking boooking = FakeDataBooking.createBooking();
        when(bookingService.findBookingById("1")).thenReturn(Optional.of(Booking));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(boooking);

        mockMvc.perform(delete(BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(BookingService, times(1)).deleteBookingById("1");
    }

    @Test
    public void testDeleteBookingNotExisting() throws Exception{
        String id = "1";
        when(bookingService.findBookingById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result-> assertTrue(result.getResolvedException() instanceof BoookingNotFoundException))
                .andExpect(result-> assertEquals("404 NOT_FOUND \" booking with ID: " + id + "not found\"", result.getResolvedException().getMessage()));

        verify(bookingService, times(0)).deleteBookingById(id);
    }

}
