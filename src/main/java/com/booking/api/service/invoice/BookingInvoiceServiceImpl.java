package com.booking.api.service.invoice;

import com.booking.api.model.BookingInvoice;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceDto;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceMapper;
import com.booking.api.model.dto.bookinginvoice.BookingInvoiceResponseDto;
import com.booking.api.repository.bookinginvoice.BookingInvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BookingInvoiceServiceImpl implements BookingInvoiceService {

    @Autowired
    private BookingInvoicesRepository bookingInvoicesRepository;
    @Override
    public List<BookingInvoiceResponseDto> getAllBookingInvoice() {
        List<BookingInvoiceResponseDto> bookingInvoices = new ArrayList<>();
        bookingInvoicesRepository.getAllBookingInvoice().forEach(bookingInvoice -> bookingInvoices.add(BookingInvoiceMapper.bookingToBookingResponseDto(bookingInvoice)));
        return bookingInvoices;
    }

    @Override
    public Optional<BookingInvoiceResponseDto> findBookingInvoiceById(String idBookingInvoice) {
        return Optional.of(BookingInvoiceMapper.bookingToBookingResponseDto(bookingInvoicesRepository.findBookingInvoiceById(idBookingInvoice)));
    }

    @Override
    public BookingInvoiceResponseDto saveBookingInvoice(BookingInvoiceDto bookingInvoiceDto) {
        BookingInvoice bookingInvoice = bookingInvoicesRepository.saveBookingInvoice(BookingInvoiceMapper.bookingInvoicesDtoToBookingInvoice(bookingInvoiceDto));
        return BookingInvoiceMapper.bookingToBookingResponseDto(bookingInvoice);
    }

    @Override
    public BookingInvoiceResponseDto updateBookingInvoice(String idBookingInvoice, BookingInvoiceDto bookingInvoiceDto) {
        BookingInvoice bookingInvoice = bookingInvoicesRepository.updateBookingInvoice(idBookingInvoice, BookingInvoiceMapper.bookingInvoicesDtoToBookingInvoice(bookingInvoiceDto));
        return BookingInvoiceMapper.bookingToBookingResponseDto(bookingInvoice);

    }

    @Override
    public Boolean deleteBookingInvoiceById(String idBookingInvoice) {
        Optional<Object> bookingInvoice = Optional.ofNullable(bookingInvoicesRepository.findBookingInvoiceById(idBookingInvoice));
        if(bookingInvoice.isPresent()){
            bookingInvoicesRepository.deleteBookingInvoiceById(idBookingInvoice);
            return true;
        }else {
            return false;
        }
    }
}
