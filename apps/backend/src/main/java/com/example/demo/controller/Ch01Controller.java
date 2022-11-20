package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 跨網域
@RestController
public class Ch01Controller {
    // get跟post不能寫一起，會錯誤
    @GetMapping("/ch01")
    String getCh01() {
        return "hello Ch01";
    }
}
