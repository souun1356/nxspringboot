package com.example.demo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseBody {
    @JsonProperty("customerId")
    public String customerId;

    @JsonProperty("name")
    public String name;

    @JsonProperty("age")
    public BigDecimal age;

    @JsonProperty("tel")
    public String tel;

    @JsonProperty("addr")
    public String addr;
}
