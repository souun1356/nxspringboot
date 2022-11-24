package com.example.demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.dao.CategoryDao;

public class CategoryDaoServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    CategoryDao dao = new CategoryDao();

    request.setAttribute("el", "test el");
    request.setAttribute("jstl", "test jstl");

    try {
      request.setAttribute("dataList", dao.findAll());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    request.getRequestDispatcher("category.jsp").forward(request, response);
  }
}
