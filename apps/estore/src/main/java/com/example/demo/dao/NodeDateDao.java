package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.example.demo.domain.NodeDate;
import com.example.demo.utils.JdbcUtils;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class NodeDateDao {
  public List<NodeDate> getCName(int level) throws SQLException {
    String sql = "select * from category where length(code)=" + (level * 2);
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<NodeDate>(NodeDate.class));
  }

  public List<NodeDate> getNextName(String code) throws SQLException {
    String sql = "select * from category where fathercode=" + code;
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<NodeDate>(NodeDate.class));
  }

  public int getCode(String name) throws SQLException {
    String sql = "select * from category where name=" + name;
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    long code = (long) runner.query(sql, new ScalarHandler());
    return (int) code;
  }

  public NodeDate getNoeDate(String name) throws SQLException {
    String sql = " select * from category where name =?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanHandler<NodeDate>(NodeDate.class), name);
  }

}
