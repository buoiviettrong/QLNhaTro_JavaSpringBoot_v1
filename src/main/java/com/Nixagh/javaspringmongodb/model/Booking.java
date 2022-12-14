package com.Nixagh.javaspringmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Booking")
public class Booking {

  @Id
  private String id;
  private String userId;
  private Motel motel;

  private String customerName;
  private String customerPhoneNumber;

  private String noteByCustomer;
  private String noteByHost;
  private String numberMonth;
  private String startDate;

//  private String Status = "Pending";

  public String getStartDate() { return startDate; }

  public void setStartDate(String startDate) { this.startDate = startDate; }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Motel getMotel() {
    return motel;
  }

  public void setMotel(Motel motel) {
    this.motel = motel;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerPhoneNumber() {
    return customerPhoneNumber;
  }

  public void setCustomerPhoneNumber(String customerPhoneNumber) {
    this.customerPhoneNumber = customerPhoneNumber;
  }

  public String getNoteByCustomer() {
    return noteByCustomer;
  }

  public void setNoteByCustomer(String noteByCustomer) {
    this.noteByCustomer = noteByCustomer;
  }

  public String getNoteByHost() {
    return noteByHost;
  }

  public void setNoteByHost(String noteByHost) {
    this.noteByHost = noteByHost;
  }

  public String getNumberMonth() {
    return numberMonth;
  }

  public void setNumberMonth(String numberMonth) {
    this.numberMonth = numberMonth;
  }

//  public String getStatus() {
//    return Status;
//  }
//
//  public void setStatus(String status) {
//    Status = status;
//  }
}
