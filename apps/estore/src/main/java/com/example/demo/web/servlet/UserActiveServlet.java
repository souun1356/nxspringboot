package com.example.demo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.exception.ActiveCodeException;
import com.example.demo.service.UserService;

/**
 * Servlet implementation class UserActiveServlet
 */
@WebServlet("/UserActiveServlet")
public class UserActiveServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UserActiveServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1.得到激活碼
    String activeCode = request.getParameter("activeCode");

    // 2.調用service中激活操作
    UserService service = new UserService();

    try {
      service.activeUser(activeCode);

      response.getWriter().write(
          "激活成功，請回<a href='http://localhost:8080/Estore'>首頁</a>");
      return;

    } catch (ActiveCodeException e) {
      // 2.1激活碼超時或者激活失敗
      request.setAttribute("active.message", e.getMessage());
      request.getRequestDispatcher("。。/error/activeuser_error.jsp").forward(request,
          response);
      return;
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
