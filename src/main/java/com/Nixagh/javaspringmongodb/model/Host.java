package com.Nixagh.javaspringmongodb.model;

import com.Nixagh.javaspringmongodb.model.Helpers.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Host")
public class Host {

  @Id
  private String id;

  private String name;
  private Address address;
  private String phoneNumber;
  private String image;

  private int rating;
  private int minPrice;
  private int maxPrice;
  private String description;
  private boolean post = false;
  private boolean active = true;

  public int getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(int minPrice) {
    this.minPrice = minPrice;
  }

  public int getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(int maxPrice) {
    this.maxPrice = maxPrice;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public boolean post() {
    return post;
  }

  public void setPost(boolean post) {
    this.post = post;
  }

  public boolean Active() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
