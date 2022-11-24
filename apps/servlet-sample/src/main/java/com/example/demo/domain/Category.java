package com.example.demo.domain;

public class Category {

  public String code;
  public String name;
  public String fathercode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFathercode() {
    return fathercode;
  }

  public void setFathercode(String fathercode) {
    this.fathercode = fathercode;
  }
}
