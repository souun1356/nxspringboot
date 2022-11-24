package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.demo.domain.Category;
import com.example.demo.util.JdbcUtils;

public class CategoryDao {

  public List<Category> findAll() throws SQLException {
    String sql = "SELECT * FROM CATEGORY";
    QueryRunner runner = new QueryRunner();
    return runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler<Category>(Category.class));
  }
}
