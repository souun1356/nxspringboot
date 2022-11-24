package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

  /**
   * 獲取資料庫連接對象
   *
   * @return
   */
  public static Connection getConnection() {
    Connection connection = null;
    try {
      // 讀取jdbc.properties文件組態
      InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
      Properties properties = new Properties();
      properties.load(is);
      is.close();
      String driverClass = properties.getProperty("driverClass");
      String jdbcUrl = properties.getProperty("jdbcUrl");
      String user = properties.getProperty("user");
      String password = properties.getProperty("password");

      // 載入資料庫驅動並註冊
      Class.forName(driverClass);

      // 通過 DriverManager類的getConnection 獲取資料庫連接對象，並返回
      connection = DriverManager.getConnection(jdbcUrl, user, password);
    } catch (IOException e) {
      System.out.println("讀取組態文件異常");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("無此資料庫驅動類");
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("連線據庫出現異常");
    }
    return connection;
  }

  /**
   * 釋放JDCB資源
   *
   * @param resultSet:  ResultSet對象
   * @param statement:  Statement對象
   * @param connection: 資料庫連接對象
   */
  public static void release(ResultSet resultSet, Statement statement, Connection connection) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void release(Statement statement, Connection connection) {
    release(null, statement, connection);
  }

  public static void release(Connection connection) {
    release(null, null, connection);
  }

  public static void release(ResultSet resultSet, Connection connection) {
    release(resultSet, null, connection);
  }

  /**
   * 開啟事務
   *
   * @param connection: Connection對象
   */
  public static void beginTransaction(Connection connection) {
    if (connection == null) {
      return;
    }
    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 提交事務
   *
   * @param connection: Connection對象
   */
  public static void commitTransaction(Connection connection) {
    if (connection == null)
      return;
    try {
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 回滾事務
   *
   * @param connection: Connection對象
   */
  public static void rollbackTransaction(Connection connection) {
    if (connection == null)
      return;
    try {
      connection.rollback();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
