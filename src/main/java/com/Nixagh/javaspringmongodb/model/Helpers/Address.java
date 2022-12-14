package com.Nixagh.javaspringmongodb.model.Helpers;

public class Address {
  private String province;
  private String district;
  private String wand;

  public Address(String province, String district, String wand) {
    this.province = province;
    this.district = district;
    this.wand = wand;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getWand() {
    return wand;
  }

  public void setWand(String wand) {
    this.wand = wand;
  }
}
