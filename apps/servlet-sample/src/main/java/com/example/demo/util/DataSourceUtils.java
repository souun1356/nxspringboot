package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class DataSourceUtils {

  // 開啟事務
  public static void startTransaction(Connection con) throws SQLException {
    if (con != null) {
      con.setAutoCommit(false);
    }
  }

  // 事務回滾
  public static void rollback(Connection con) throws SQLException {
    if (con != null) {
      con.rollback();
    }
  }

  public static void closeConnection(Connection con) throws SQLException {
    if (con != null) {
      con.commit();// 事務提交
      con.close();
    }
  }

  /**
   * 當DBUtils需要手動控制事務時，調用該方法獲得一個連接
   *
   * @return
   * @throws SQLException
   */
  public static Connection getConnection() throws SQLException {
    String jdbcURL = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf8";
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String password = "";

    DbUtils.loadDriver(jdbcDriver);
    return DriverManager.getConnection(jdbcURL, user, password);
  }
}
