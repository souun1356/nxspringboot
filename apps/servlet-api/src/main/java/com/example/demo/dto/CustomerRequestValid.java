package com.example.demo.dto;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestValid {

  @JsonProperty("header")
  public CommonHeader header;

  @Valid
  @JsonProperty("body")
  public CustomerRequestValidBody body;
}
