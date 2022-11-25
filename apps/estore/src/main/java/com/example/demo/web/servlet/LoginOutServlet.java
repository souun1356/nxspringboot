package com.example.demo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginOutServlet
 */
@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    /**
     * 註：***************
     * 正常的cookie只能在一個應用中共享，即一個cookie只能由創建它的應用獲得。
     * 可在同一應用服務器內共享方法：設置cookie.setPath("/");
     */
    // 註銷功能就是銷毀session

    request.getSession().invalidate();

    // 將自動登錄的cookie刪除。

    Cookie cookie = new Cookie("autologin", "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);

    response.sendRedirect(request.getContextPath() + "/index.jsp");

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
