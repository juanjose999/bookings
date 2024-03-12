package com.booking.api.repository.bookinginvoice;

import com.booking.api.model.BookingInvoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingInvoicesMongoRepository extends MongoRepository<BookingInvoice, String> {
}
