package com.Nixagh.javaspringmongodb.repository;

import com.Nixagh.javaspringmongodb.model.Booking;
import com.Nixagh.javaspringmongodb.model.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {
  List<Receipt> findByStatusAndHostId(boolean status, String hostId);
  List<Receipt> findByBooking_UserIdAndStatus(String id, boolean status);
}
