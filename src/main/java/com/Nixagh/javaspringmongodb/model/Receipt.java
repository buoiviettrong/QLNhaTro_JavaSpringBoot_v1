package com.Nixagh.javaspringmongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Receipt")
public class Receipt {
  @Id
  private String id;
  private Booking booking;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private String endDate = String.valueOf(new Date());
  private boolean status = false;
  private String hostId;

  public String gethostId() { return hostId; }
  public void sethostId(String hostId) { this.hostId = hostId;}

  public  boolean getStatus() { return status; }
  public void setStatus(boolean status) { this.status = status;}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
