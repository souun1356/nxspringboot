package com.example.demo.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommonHeaderResponse;
import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.dto.CustomerResponseBody;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

// 用性別查詢客戶
@CrossOrigin
@RestController
public class Ch05Controller {
    @Autowired
    public CustomerService customerService;

    @PostMapping("/ch05")
    public CustomerResponse ch05(@RequestBody CustomerRequest request) {
        CustomerResponse response = new CustomerResponse();
        List<CustomerResponseBody> dataList = new LinkedList<>();

        CommonHeaderResponse header = new CommonHeaderResponse();
        BeanUtils.copyProperties(request.getHeader(), header);

        String gender = request.getBody().getGender();
        // List<Customer> customers = this.customerService.findByGender(gender);
        // var customers = this.customerService.findCustomerByGender(gender);
        // var customers = this.customerService.findAllCustomersByGenderNative(gender);
        var customers = this.customerService.findCustomerByGenderStream(gender);
        // var customers = this.customerService.findCustomerNameByGenderNative(gender);

        // var customers = this.customerService.findAllMaleCustomers();
        // var customers = this.customerService.findAllMaleCustomersNative();

        if (customers != null && !customers.isEmpty()) {
            header.setCode("0000");
            header.setMsg("成功");

            // 多表的interface型態
            // for (CustomerResponseDtoBody customer : customers) {
            for (Customer customer : customers) {
                CustomerResponseBody responseBody = new CustomerResponseBody();
                BeanUtils.copyProperties(customer, responseBody);
                dataList.add(responseBody);
            }
        } else {
            header.setCode("9999");
            header.setMsg("查無資料");
        }

        response.setHeader(header);
        response.setBody(dataList);

        return response;
    }

}
