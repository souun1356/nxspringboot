package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestValidBody {

  public String customerId;

  @NotBlank(message = "性別不能為空白")
  public String gender;
}
