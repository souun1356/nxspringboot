package com.example.demo.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.example.demo.domain.Order;
import com.example.demo.exception.addOrderException;
import com.example.demo.service.OrderService;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1.得到實體類對象，將數據封裝至實體類中
    Order order = new Order();
    try {
      BeanUtils.populate(order, request.getParameterMap());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    OrderService service = new OrderService();
    try {
      service.addOrder(order);
      response.sendRedirect(request.getContextPath()
          + "/index.jsp");
      return;
    } catch (addOrderException e) {
      request.setAttribute("addOrder.message", e.getMessage());
      request.getRequestDispatcher("/error/addOrder_error.jsp").forward(request,
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
