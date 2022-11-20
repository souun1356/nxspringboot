package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    public Customer findByCustomerId(String customerId) {
        // CustomerRepository可以用findById()直接查詢ID主鍵，findById()回傳Optional型態，
        // 不用在CustomerRepository寫一個查詢id
        var op = this.customerRepository.findById(customerId);
        // isPresent()檢查值是否存在，如果值存在，回傳 true；不存在則回傳 false。
        // get()如果值存在就回傳這個值，否則就丟出 NoSuchElementException。
        return op.isPresent() ? op.get() : null;
    }
}
