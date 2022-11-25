package com.example.demo.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

/**
 * Servlet implementation class AddProductToCartServlet
 */
@WebServlet("/AddProductToCartServlet")
@SuppressWarnings("unchecked")
public class AddProductToCartServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddProductToCartServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1.得到商品id
    String id = request.getParameter("id");

    // 2.根據id查詢商品
    ProductService service = new ProductService();
    try {
      Product p = service.findById(Integer.parseInt(id));

      // 3.將商品添加到購物車
      HttpSession session = request.getSession();
      // 從session中獲取購物車
      Map<Product, Integer> cart = (Map<Product, Integer>) session
          .getAttribute("cart");
      // 如果cart為null,說明，沒有購物車，是第一次購物
      if (cart == null) {
        // 創建出購物車
        cart = new HashMap<Product, Integer>();
      }
      // 判斷購物車中是滯有當前要買的商品
      Integer count = cart.get(p);
      if (count == null) {
        // 如果為null,說明購物車中沒有這個商品，這時商品的數量就為1
        count = 1;
      } else {
        // 如果不為null,說明購物車中有這個商品，這時，就將商品的數量+1
        count += 1;
      }
      // 將商品存儲到購物車中
      cart.put(p, count);
      // 將購物車存儲到session中.
      session.setAttribute("cart", cart);

      response.sendRedirect(request.getContextPath() + "/addProductToCartSuccessfull.jsp");
      // response.getWriter().write("添加商品到購物車成功，<a
      // href='http://localhost:8080/estore'>繼續購物</a>,<a
      // href='http://localhost:8080/estore/showcart.jsp'>查看購物車</a>");
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
