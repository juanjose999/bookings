package com.booking.api.repository.invoice;

import com.booking.api.model.BookingInvoices;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingInvoicesMongoRepository extends MongoRepository<BookingInvoices, String> {
}
