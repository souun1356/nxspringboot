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
import com.example.demo.service.ProductService;

/**
 * Servlet implementation class ProductmmServlet
 */
@WebServlet("/ProductSimpleServlet")
public class ProductSimpleServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 得到請求參數
    String field = request.getParameter("field"); // 字段名稱
    String msg = request.getParameter("msg"); // 字段值

    // 調用service查詢操作
    ProductService service = new ProductService();

    try {
      List<Product> pro = service.simpleSelect(field, msg);

      request.setAttribute("pro", pro);

      request.getRequestDispatcher("/showProducts.jsp").forward(request,
          response);
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
