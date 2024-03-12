package com.booking.api.model.dto.bookinginvoice;

import com.booking.api.model.BookingInvoice;

public class BookingInvoiceMapper {

    public static BookingInvoice bookingInvoicesDtoToBookingInvoice(BookingInvoiceDto bookingInvoiceDto){
        return new BookingInvoice(
                bookingInvoiceDto.getDateIssueInvoice(),
                bookingInvoiceDto.getUserDetailsList(),
                bookingInvoiceDto.getDetailsTrip(),
                bookingInvoiceDto.getInvoiceSellerName()
        );
    }

    public static BookingInvoiceResponseDto bookingToBookingResponseDto(BookingInvoice bookingInvoice){
        return new BookingInvoiceResponseDto(
                bookingInvoice.getIdInvoice(),
                bookingInvoice.getDateIssueInvoice(),
                bookingInvoice.getUserDetailsList(),
                bookingInvoice.getDetailsTrip(),
                bookingInvoice.getInvoiceSellerName()
        );
    }
}
