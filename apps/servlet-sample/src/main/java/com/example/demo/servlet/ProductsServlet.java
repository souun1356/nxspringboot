package com.example.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.dao.ProductDao;

public class ProductsServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    ProductDao dao = new ProductDao();

    request.setAttribute("el", "test el");
    request.setAttribute("jstl", "test jstl");
    request.setAttribute("dataList", dao.findAll("select * from products"));
    request.setAttribute("dataList2", dao.findAll());

    request.getRequestDispatcher("products.jsp").forward(request, response);
  }
}
