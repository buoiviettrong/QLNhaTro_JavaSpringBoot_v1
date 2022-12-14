package com.Nixagh.javaspringmongodb.model;

import com.Nixagh.javaspringmongodb.model.Enum.EStatus;
import com.Nixagh.javaspringmongodb.model.Helpers.MotelType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Motel")
public class Motel {

  @Id
  private String id;
  private String name;

  private int area;
  private String type;
  private int bedNumber;
  private String image;
  private int price;
  private long electricityPrice;
  private long waterPrice;
  private String status = "Space";
  private String hostId;

  public String getHostId() {
    return hostId;
  }

  public void setHostId(String hostId) {
    this.hostId = hostId;
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

  public int getArea() {
    return area;
  }

  public void setArea(int area) {
    this.area = area;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getBedNumber() {
    return bedNumber;
  }

  public void setBedNumber(int bedNumber) {
    this.bedNumber = bedNumber;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public long getElectricityPrice() {
    return electricityPrice;
  }

  public void setElectricityPrice(long electricityPrice) {
    this.electricityPrice = electricityPrice;
  }

  public long getWaterPrice() {
    return waterPrice;
  }

  public void setWaterPrice(long waterPrice) {
    this.waterPrice = waterPrice;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
