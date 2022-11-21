package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommonHeaderResponse;
import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.dto.CustomerResponseDtoBody;
import com.example.demo.service.CustomerService;

// 查詢性別為男性的客戶姓名
@CrossOrigin
@RestController
public class Ch06Controller {
    @Autowired
    public CustomerService customerService;

    @PostMapping("/ch06")
    public CustomerResponseDto ch06(@RequestBody CustomerRequest request) {
        CustomerResponseDto response = new CustomerResponseDto();

        CommonHeaderResponse header = new CommonHeaderResponse();
        BeanUtils.copyProperties(request.getHeader(), header);

        String gender = request.getBody().getGender();
        List<CustomerResponseDtoBody> customers = this.customerService.findCustomerNameByGenderNative(gender);

        if (customers != null && !customers.isEmpty()) {
            header.setCode("0000");
            header.setMsg("成功");
        } else {
            header.setCode("9999");
            header.setMsg("查無資料");
        }

        response.setHeader(header);
        response.setBody(customers);

        return response;
    }
}
