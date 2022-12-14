package com.Nixagh.javaspringmongodb.repository;

import com.Nixagh.javaspringmongodb.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

  Booking getBookingByMotelId(String id);

  Booking findByUserId(String id);
}
