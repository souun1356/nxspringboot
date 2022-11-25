package com.example.demo.service;

import java.sql.SQLException;

import com.example.demo.dao.OrderDao;
import com.example.demo.domain.Order;
import com.example.demo.exception.addOrderException;

public class OrderService {
  public int addOrder(Order order) throws addOrderException {
    OrderDao dao = new OrderDao();
    int i = 0;

    try {
      i = dao.addOrder(order);
    } catch (SQLException e) {
      throw new addOrderException("訂單提交失敗");
    }
    return i;
  }

}
