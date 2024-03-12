package com.booking.api.repository.invoice;

import com.booking.api.model.BookingInvoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class BookingInvoicesRepositoryImpl implements BookingInvoicesRepository{

    @Autowired
    private BookingInvoicesMongoRepository bookingInvoicesMongoRepository;
    @Override
    public List<BookingInvoices> getAllBookingInvoice() {
        return bookingInvoicesMongoRepository.findAll();
    }

    @Override
    public Optional<BookingInvoices> findUserById(String idBookingInvoice) {
        Optional<BookingInvoices> findBookingInvoice = bookingInvoicesMongoRepository.findById(idBookingInvoice);
        return Optional.empty();
    }

    @Override
    public BookingInvoices saveUser(BookingInvoices bookingInvoices) {
        return bookingInvoicesMongoRepository.save(bookingInvoices);
    }

    @Override
    public BookingInvoices updateUser(String idBookingInvoice, BookingInvoices bookingInvoices) {
        Optional<BookingInvoices> findInvoice = bookingInvoicesMongoRepository.findById(idBookingInvoice);
        if(findInvoice.isPresent()){
            BookingInvoices existingInvoice = findInvoice.get();
            existingInvoice.setInvoicesIssueDate(bookingInvoices.getInvoicesIssueDate());
            existingInvoice.setUserDetailsList(bookingInvoices.getUserDetailsList());
            existingInvoice.setDetailsTrip(bookingInvoices.getDetailsTrip());
            bookingInvoicesMongoRepository.save(existingInvoice);
            return existingInvoice;
        }else {
            throw  new InvoiceNotFoundExceptioin("Invoice with ID: " + idBookingInvoice + "not found.");
        }
    }

    @Override
    public Boolean deleteBookingById(String idBookingInvoice) {
        Optional<BookingInvoices> findInvoice = bookingInvoicesMongoRepository.findById(idBookingInvoice);
        if (findInvoice.isPresent()){
            bookingInvoicesMongoRepository.deleteById(idBookingInvoice);
            return true;
        }else {
            return false;
        }
    }
}
