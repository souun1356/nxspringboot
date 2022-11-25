package com.example.demo.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.ProductService;

/**
 * Servlet implementation class ProductDelSelectServlet
 */
@WebServlet("/ProductDelSelectServlet")
public class ProductDelSelectServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 得到刪除所有商品id
    String[] idstring = (request.getParameterValues("ck"));

    // 將String類型數組轉化成int類型數組
    int[] id = new int[idstring.length];
    for (int i = 0; i < id.length; i++) {
      id[i] = Integer.parseInt(idstring[i]);
    }

    // 調用service中刪除所選數據
    ProductService service = new ProductService();

    try {
      service.delSelect(id);

      request.getRequestDispatcher("/ProductFindAllServlet").forward(request, response);
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
