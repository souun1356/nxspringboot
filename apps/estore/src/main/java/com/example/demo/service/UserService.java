package com.example.demo.service;

import java.security.GeneralSecurityException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.login.LoginException;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.exception.ActiveCodeException;
import com.example.demo.exception.RegistException;
import com.example.demo.utils.MailUtils;

public class UserService {
  /* 1.註冊操作 START */
  public void regist(User user) throws RegistException {

    UserDao dao = new UserDao();
    try {
      // 1.添加註冊用戶信息
      dao.addUser(user);
      // 2.向註冊用戶發送激活郵件
      String emailMsg = "註冊成功，請點擊下列連接已完成激活操作:(ps:由於郵箱原因，請複製鏈接打開！)" + "<br>" +
          "http://localhost:8080/Estore/UserActiveServlet?activeCode=" + user.getActivecode();
      MailUtils.sendMail(user.getEmail(), emailMsg);
    } catch (SQLException e) {
      throw new RegistException("註冊失敗");
    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
  }
  /* 1. 註冊操作 END */

  /* 2. 激活用戶操作 START */
  public void activeUser(String activeCode) throws ActiveCodeException {
    UserDao dao = new UserDao();
    // 1.根據激活碼查詢用戶，要判斷激活碼是否過期.

    try {
      User user = dao.findUserByActiveCode(activeCode);

      if (user != null) {
        // 2.進行激活操作

        long time = System.currentTimeMillis()
            - user.getUpdatetime().getTime();

        if (time <= 24 * 60 * 1000 * 60) {
          // 激活
          try {
            dao.activeUser(activeCode);
          } catch (SQLException e) {
            throw new ActiveCodeException("激活用戶失敗");
          }

        } else {
          throw new ActiveCodeException("激活碼過期");
        }
      } else {
        throw new ActiveCodeException("用戶不存在");
      }
    } catch (SQLException e) {
      throw new ActiveCodeException("查找激活用戶失敗");
    }
  }
  /* 2. 激活用戶操作 START */

  /* 3.登錄操作 START */
  public User login(String username, String password) throws LoginException, ActiveCodeException {
    // 需要注意用戶是否激活
    UserDao dao = new UserDao();
    try {
      User user = dao.findUserByLogin(username, password);
      if (user != null) {
        // 判斷用戶是否激活
        if (user.getState() == 1) {
          return user;
        } else {
          throw new ActiveCodeException("用戶未激活");
        }
      } else {
        throw new LoginException("用戶名或密碼錯誤");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new LoginException("用戶名或密碼錯誤");
    }

  }

  /* 3.登錄操作 END */
}
