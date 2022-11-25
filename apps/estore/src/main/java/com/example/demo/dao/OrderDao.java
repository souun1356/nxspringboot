package com.example.demo.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.example.demo.domain.Order;
import com.example.demo.utils.JdbcUtils;

public class OrderDao {

  // 添加訂單操作
  public int addOrder(Order order) throws SQLException {
    String sql = "insert into orders values(null,?,?,?,null,null)";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.update(sql, order.getMoney(), order.getReceiverinfo(), order.getPaystate());
  }

}
