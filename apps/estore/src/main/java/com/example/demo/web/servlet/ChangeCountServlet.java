package com.example.demo.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.Product;

/**
 * Servlet implementation class ChangeCountServlet
 */
@WebServlet("/ChangeCountServlet")
@SuppressWarnings("unchecked")
public class ChangeCountServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1.得到請求參數
    int id = Integer.parseInt(request.getParameter("id"));
    int count = Integer.parseInt(request.getParameter("count"));

    // 2.修稿購物車的指定數量
    // 2.1得到購物車
    Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
    // 2.2修改購物車中的商品
    Product p = new Product();
    p.setId(id);
    if (count == 0) {
      // 刪除商品
      cart.remove(p);
    } else {
      cart.put(p, count);
    }
    response.sendRedirect(request.getContextPath() + "/showcart.jsp");
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
