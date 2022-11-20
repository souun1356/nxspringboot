package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CustomerResponseDtoBody;
import com.example.demo.entity.Customer;

@Repository // 可加可不加
// 定義一個跟資料表同名的Repository資料庫, <類別,pk(customerId)>
public interface CustomerRepository extends JpaRepository<Customer, String> {

    // https://www.baeldung.com/spring-data-jpa-query

    // 類別查詢
    // 對資料庫的操作, 同findCustomerByGender()
    List<Customer> findByGender(String gender);

    // 用Customer 類別名稱查詢男性客戶
    @Query("SELECT c FROM Customer c WHERE c.gender = 'M'")
    List<Customer> findAllMaleCustomers();

    // 一個參數，用@Param("gender")儲存到引數gender在資料庫指令用:gender來當條件查詢
    // 按性別尋找客戶
    @Query("SELECT c FROM Customer c WHERE c.gender = :gender")
    List<Customer> findCustomerByGender(@Param("gender") String gender);

    // 原生資料庫查詢
    // 用 CUSTOMER 資料表原生查詢，2個參數，sql要加value, nativeQuery = true為使用原生查詢
    // 找到所有男性客戶
    @Query(value = "SELECT * FROM CUSTOMER WHERE GENDER = 'M'", nativeQuery = true)
    List<Customer> findAllMaleCustomersNative();

    // 按性別查找所有客戶
    @Query(value = "SELECT * FROM CUSTOMER c WHERE c.GENDER = :gender", nativeQuery = true)
    List<Customer> findAllCustomersByGenderNative(@Param("gender") String gender);

    // 按性別查找客戶姓名
    @Query(value = "SELECT c.CUSTOMER_ID as customerId, c.NAME as name FROM CUSTOMER c WHERE c.GENDER = :gender", nativeQuery = true)
    List<CustomerResponseDtoBody> findCustomerNameByGenderNative(@Param("gender") String gender);

}
