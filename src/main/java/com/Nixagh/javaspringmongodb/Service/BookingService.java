package com.Nixagh.javaspringmongodb.Service;

import com.Nixagh.javaspringmongodb.model.Booking;
import com.Nixagh.javaspringmongodb.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
  @Autowired
  BookingRepository bookRepository;

  public Booking getBooking(String id) {
    return bookRepository.getBookingByMotelId(id);
  }
  public Booking addBooking(Booking booking) {
    return bookRepository.insert(booking);
  }
  public void delete(Booking booking) {
    bookRepository.delete(booking);
  }
  public Booking findByUserId(String id) {
    return bookRepository.findByUserId(id);
  }
}
