package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // 包含get set
@ToString
@NoArgsConstructor // 無參數建構子
@AllArgsConstructor // 全部參數建構子
public class CustomerRequest {
    // 大小寫一樣就可以不用設定@JsonProperty
    @JsonProperty("header")
    public CommonHeader header;
    @JsonProperty("body")
    public CustomerRequestBody body;
}
