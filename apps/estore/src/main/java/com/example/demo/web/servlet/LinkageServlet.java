package com.example.demo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.NodeDateService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LinkageServlet
 */
@WebServlet("/LinkageServlet")
public class LinkageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String code = request.getParameter("levelcode");
    NodeDateService service = new NodeDateService();
    if (code != null && !code.equals("")) {// 有變量就要進行空判斷
      try {
        List<HashMap<String, Object>> name = service.getNextName(code);
        request.getSession().setAttribute("c3name", name);
        // 將集合對像轉換成json格式---List---JsonArr字符串[{},{},{}]
        String aString = new ObjectMapper().writeValueAsString(name);
        out.print(aString);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      out.print("fail");
      // 查詢失敗
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
