package com.Nixagh.javaspringmongodb.model;

import com.Nixagh.javaspringmongodb.model.Enum.orderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Order")
public class Order {

  @Id
  private String id;
  private User user;
  private Motel motel;

  private Date orderTime;
  private Date acceptTime;
  private orderStatus status = orderStatus.Pending;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Motel getMotel() {
    return motel;
  }

  public void setMotel(Motel motel) {
    this.motel = motel;
  }

  public Date getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  public Date getAcceptTime() {
    return acceptTime;
  }

  public void setAcceptTime(Date acceptTime) {
    this.acceptTime = acceptTime;
  }

  public orderStatus getStatus() {
    return status;
  }

  public void setStatus(orderStatus status) {
    this.status = status;
  }
}
