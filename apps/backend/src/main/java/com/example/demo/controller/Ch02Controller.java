package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Ch02Controller {
    // application.properties 設定使用哪個-xx.properties設定檔
    // 設定檔的鍵值env.name，對應屬性envName
    @Value("${env.name}")
    private String envName;

    @GetMapping("/ch02")
    String getEnvName() {
        return envName;
    }
}
