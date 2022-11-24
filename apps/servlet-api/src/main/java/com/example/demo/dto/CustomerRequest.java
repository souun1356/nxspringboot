package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

  @JsonProperty("header")
  public CommonHeader header;

  @JsonProperty("body")
  public CustomerRequestBody body;
}
