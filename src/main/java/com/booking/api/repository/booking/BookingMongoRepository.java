package com.booking.api.repository.booking;

import com.booking.api.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookingMongoRepository extends MongoRepository<Booking, String> {
}
