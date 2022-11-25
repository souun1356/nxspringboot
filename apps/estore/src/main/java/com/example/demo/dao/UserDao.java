package com.example.demo.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.example.demo.domain.User;
import com.example.demo.utils.JdbcUtils;

public class UserDao {
    // 1. 註冊操作
    public void addUser(User user) throws SQLException {

        String sql = "insert into users values(null,?,?,?,?,?,?,null)";

        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

        runner.update(sql, user.getUsername(),
                user.getPassword(), user.getEmail(), "user", 0, user.getActivecode());
    }// 註：用戶註冊密碼為了安全起見，要對密碼進行加密，該項目中有Md5加密工具，防止書記洩露，，本次為了節約時間，此部分略過。。。。

    // 2. 查找用戶，根據激活碼
    public User findUserByActiveCode(String activeCode) throws SQLException {

        String sql = "select * from users where activecode=?";

        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

        return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
    }

    // 3. 激活用戶
    public void activeUser(String activeCode) throws SQLException {

        String sql = "update users set state=1 where activecode=?";

        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

        runner.update(sql, activeCode);
    }

    // 4. 登錄操作
    public User findUserByLogin(String username, String password) throws SQLException {
        if (username.contains("@")) {

            String sql = "select * from users where email=? and password=?";
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            return runner.query(sql, new BeanHandler<User>(User.class), username,
                    password);

        } else {
            String sql = "select * from users where username=? and password=?";
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            return runner.query(sql, new BeanHandler<User>(User.class), username,
                    password);
        }
    }

}
