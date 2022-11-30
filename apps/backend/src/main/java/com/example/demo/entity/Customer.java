package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // get set
@ToString
@NoArgsConstructor // 無參數建構子
@AllArgsConstructor // 所有參數建構子
@Entity(name = "Customer") // 類別名稱
@Table(name = "CUSTOMER") // 資料表名稱
public class Customer {
    // jpa實現ORM機制
    @Id // pk主鍵
    // 資料表欄位有下底線的要加，不然會找customerId找不到有底線的
    @Column(name = "CUSTOMER_ID")
    public String customerId;
    public String name;
    public String tel;
    public String addr;
    public String gender;
    public BigDecimal age;
}
