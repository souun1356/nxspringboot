package com.example.demo.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;

/**
 * Servlet implementation class ProductFindAllServlet
 */
@WebServlet("/ProductFindAllServlet")
public class ProductFindAllServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 調用service中查詢所有方法
    ProductService service = new ProductService();

    try {
      List<Product> pro = service.findAll();

      request.setAttribute("pro", pro);

      List<Product> pro1 = service.findByCode("51");

      request.setAttribute("pro1", pro1);

      List<Product> pro2 = service.findByCode("52");

      request.setAttribute("pro2", pro2);

      List<Product> pro3 = service.findByCode("53");

      request.setAttribute("pro3", pro3);

      List<Product> pro4 = service.findByCode("54");

      request.setAttribute("pro4", pro4);

      User user = (User) request.getSession().getAttribute("user");

      if (user == null || user.getRole().equals("user")) {
        request.getRequestDispatcher("/home.jsp").forward(request, response);
        return;
      }
      request.getRequestDispatcher("/showProducts.jsp").forward(request, response);
      return;

    } catch (SQLException e) {
      e.printStackTrace();
      response.getWriter().write(e.getMessage());
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
