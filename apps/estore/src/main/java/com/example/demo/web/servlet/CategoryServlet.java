package com.example.demo.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.NodeDate;
import com.example.demo.domain.User;
import com.example.demo.service.NodeDateService;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    NodeDateService service = new NodeDateService();
    try {
      List<NodeDate> onelevel = service.getCName(1);
      request.getSession().setAttribute("onelevel", onelevel);
      User user = (User) request.getSession().getAttribute("user");
      if (user == null || user.getRole().equals("user")) {
        request.getRequestDispatcher("/NewFile3.jsp").forward(request, response);
        return;
      }
      request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
      return;
    } catch (SQLException e) {
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
