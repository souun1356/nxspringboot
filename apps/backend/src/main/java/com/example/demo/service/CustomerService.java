package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerResponseDtoBody;
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

    // 實作CustomerRepository的方法, 按性別查詢客戶
    public List<Customer> findByGender(String gender) {
        return this.customerRepository.findByGender(gender);
    }

    // 用Customer 類別名稱查詢男性客戶
    public List<Customer> findAllMaleCustomers() {
        return this.customerRepository.findAllMaleCustomers();
    }

    public List<Customer> findCustomerByGender(String gender) {
        return this.customerRepository.findCustomerByGender(gender);
    }

    public List<Customer> findAllMaleCustomersNative() {
        return this.customerRepository.findAllMaleCustomersNative();
    }

    public List<Customer> findAllCustomersByGenderNative(String gender) {
        return this.customerRepository.findAllCustomersByGenderNative(gender);
    }

    public List<CustomerResponseDtoBody> findCustomerNameByGenderNative(String gender) {
        return this.customerRepository.findCustomerNameByGenderNative(gender);
    }

    // 查詢指定性別資料，用findAll()查詢，用stream過濾資料，CustomerRepository不用寫
    public List<Customer> findCustomerByGenderStream(String gender) {
        // 同下 List<Customer> customers = this.customerRepository.findAll();
        var customers = this.customerRepository.findAll();
        // o自定變數，過濾條件(gender上文必須跟資料庫的一樣)後印出資料(toList)
        return customers.stream().filter(o -> gender.equals(o.getGender())).toList();
    }

}
