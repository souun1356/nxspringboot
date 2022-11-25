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
 * Servlet implementation class RemoveProductFromCartServlet
 */
@WebServlet("/RemoveProductFromCartServlet")
@SuppressWarnings("unchecked")
public class RemoveProductFromCartServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 得到要刪除的商品的id
    int id = Integer.parseInt(request.getParameter("id"));
    // 得到購物車，從購物車中將商品刪除,
    Map<Product, Integer> cart = (Map<Product, Integer>) request
        .getSession().getAttribute("cart");

    Product p = new Product();
    p.setId(id);

    cart.remove(p);
    // 如果購物車中無商品，將購物車刪除。
    if (cart.size() == 0) {
      request.getSession().removeAttribute("cart");
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
