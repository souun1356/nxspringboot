package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository // 可加可不加
// 定義一個跟資料表同名的Repository類別, <類別,pk(customerId)>
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
