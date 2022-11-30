package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommonHeaderResponse;
import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.dto.CustomerResponseBody;

@CrossOrigin
@RestController
public class Ch03Controller {
    @PostMapping("/ch03")
    // @RequestBody接收前端傳來的json格式資料，會依照key的"header"跟"body"對應屬性儲存資料
    public CustomerResponse ch03(@RequestBody CustomerRequest request) {
        // 儲存header跟body的資料回應給前端
        CustomerResponse response = new CustomerResponse();
        // 回應body資料給前端
        CustomerResponseBody responseBody = new CustomerResponseBody();
        // 回應header資料給前端
        CommonHeaderResponse header = new CommonHeaderResponse();
        // 複製Java Bean或POJO的properties值, 參數1為被複製
        // 將request取到的資料複製到header回應給前端
        BeanUtils.copyProperties(request.getHeader(), header);
        header.setCode("0000");
        header.setMsg("成功");

        responseBody.setCustomerId(request.getBody().getCustomerId());
        responseBody.setName("Alan");
        responseBody.setTel("0912345678");
        responseBody.setAddr("地址");
        responseBody.setAge(BigDecimal.valueOf(20));

        response.setHeader(header);
        // CustomerResponse的body屬性為list型態，將屬性轉成list
        response.setBody(Arrays.asList(responseBody));

        return response;
    }
}
