package com.example.demo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.PageBean;
import com.example.demo.service.ProductService;

/**
 * Servlet implementation class ProductFindByPageServlet
 */
@WebServlet("/ProductFindByPageServlet")
public class ProductFindByPageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 設置默認訪問第一頁
    int pageNum = 1;

    String _pageNum = request.getParameter("pageNum");
    if (_pageNum != null) {
      pageNum = Integer.parseInt(_pageNum);
    }

    // 規定每頁顯示條數
    int currentPage = 12;
    String _currentPage = request.getParameter("currentPage");
    if (_currentPage != null) {
      currentPage = Integer.parseInt(_currentPage);
    }

    // 調用service中查詢操作
    ProductService service = new ProductService();

    PageBean pb = service.findByPage(pageNum, currentPage);

    request.setAttribute("pb", pb);

    request.getRequestDispatcher("/home.jsp").forward(
        request, response);

    return;

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
