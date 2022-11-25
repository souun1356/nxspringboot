package com.example.demo.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.example.demo.domain.User;
import com.example.demo.exception.ActiveCodeException;
import com.example.demo.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoginServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1.得到所有請求參數，封裝到User對像中.
    User user = new User();

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try {
      BeanUtils.populate(user, request.getParameterMap());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    // 2.校驗用戶名密碼數據是否為空為空，
    // 如果用戶名密碼為空，即Map集合中擁有返回的錯誤信息，即Map的集合有大小
    Map<String, String> map = user.validation();

    if (map.size() != 0) {
      request.setAttribute("map", map);
      request.getRequestDispatcher("/home.jsp").forward(request,
          response);
      return;
    }

    // 3.調用service中登錄的方法
    UserService service = new UserService();
    try {
      User user1 = service.login(username, password);

      // 登錄成功

      // 判斷是否勾選了記住用戶名.
      String remember = request.getParameter("remember");
      if ("on".equals(remember)) {
        // 勾選了--考慮中文問題
        Cookie cookie = new Cookie("remember", URLEncoder.encode(
            user1.getUsername(), "utf-8"));
        cookie.setMaxAge(10 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
      } else {
        // 如果用戶沒有勾選記住用戶名，將cookie刪除。刪除cookie，只需要設置maxage=0或-1,注意：要與cookie的path一致.
        Cookie cookie = new Cookie("remember", URLEncoder.encode(
            user1.getUsername(), "utf-8"));
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
      }

      // 自動登錄

      String autologin = request.getParameter("autologin");
      if ("on".equals(autologin)) {
        Cookie cookie = new Cookie("autologin", URLEncoder.encode(
            user1.getUsername(), "utf-8") + "::" + password);
        cookie.setMaxAge(10 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
      } else {
        Cookie cookie = new Cookie("autologin", URLEncoder.encode(
            user1.getUsername(), "utf-8") + "::" + password);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
      }

      request.getSession().invalidate();// 先銷毀session。

      request.getSession().setAttribute("user", user1);// 登錄成功，將user存儲到session中.

      response.sendRedirect("http://localhost:8080/Estore"); // 請求轉發只能在本站內跳轉........登錄成功應該加上用戶信息*******
      return;

    } catch (ActiveCodeException e) {
      e.printStackTrace();
      request.setAttribute("login.message", e.getMessage());
      request.getRequestDispatcher("/home.jsp")
          .forward(request, response);
      return;
    } catch (javax.security.auth.login.LoginException e) {
      e.printStackTrace();
    }

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
