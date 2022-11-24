package com.example.demo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.Category;

public class CategoryServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    List<Category> dataList = new LinkedList<>();

    Connection conn = null;
    try {
      // db parameters
      String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf8";
      String user = "root";
      String password = "";

      // create a connection to the database
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, password);
      // more processing here
      String sql = "SELECT code, name, fathercode FROM category";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      // loop through the result set
      while (rs.next()) {
        Category data = new Category();

        data.setCode(rs.getString("code"));
        data.setName(rs.getString("name"));
        data.setFathercode(rs.getString("fathercode"));

        dataList.add(data);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }

    request.setAttribute("el", "test el");
    request.setAttribute("jstl", "test jstl");
    request.setAttribute("dataList", dataList);

    request.getRequestDispatcher("category.jsp").forward(request, response);
  }
}
