package com.booking.api.repository.bookinginvoice;

import com.booking.api.model.BookingInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class BookingInvoicesRepositoryImpl implements BookingInvoicesRepository{

    @Autowired
    private BookingInvoicesMongoRepository bookingInvoicesMongoRepository;
    @Override
    public List<BookingInvoice> getAllBookingInvoice() {
        return bookingInvoicesMongoRepository.findAll();
    }

    @Override
    public BookingInvoice findBookingInvoiceById(String idBookingInvoice) {
        Optional<BookingInvoice> findBookingInvoice = bookingInvoicesMongoRepository.findById(idBookingInvoice);
        return Optional.empty();
    }

    @Override
    public BookingInvoice saveBookingInvoice(BookingInvoice bookingInvoice) {
        return bookingInvoicesMongoRepository.save(bookingInvoice);
    }

    @Override
    public BookingInvoice updateBookingInvoice(String idBookingInvoice, BookingInvoice bookingInvoice) {
        Optional<BookingInvoice> findInvoice = bookingInvoicesMongoRepository.findById(idBookingInvoice);
        if(findInvoice.isPresent()){
            BookingInvoice existingInvoice = findInvoice.get();
            existingInvoice.setInvoicesIssueDate(bookingInvoice.getInvoicesIssueDate());
            existingInvoice.setUserDetailsList(bookingInvoice.getUserDetailsList());
            existingInvoice.setDetailsTrip(bookingInvoice.getDetailsTrip());
            bookingInvoicesMongoRepository.save(existingInvoice);
            return existingInvoice;
        }else {
            throw  new InvoiceNotFoundException("Invoice with ID: " + idBookingInvoice + "not found.");
        }
    }

    @Override
    public void deleteBookingInvoiceById(String idBookingInvoice) {
        Optional<BookingInvoice> findInvoice = bookingInvoicesMongoRepository.findById(idBookingInvoice);
        if (findInvoice.isPresent()){
            bookingInvoicesMongoRepository.deleteById(idBookingInvoice);
        }else {
        }
    }
}
