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
public class CommonHeaderResponse {

    @JsonProperty("funId")
    public String funId;

    @JsonProperty("key")
    public String key;

    @JsonProperty("role")
    public String role;

    @JsonProperty("clientIp")
    public String clientIp;

    @JsonProperty("code")
    public String code;

    @JsonProperty("msg")
    public String msg;
}
