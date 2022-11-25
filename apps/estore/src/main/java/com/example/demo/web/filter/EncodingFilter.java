package com.example.demo.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 通用解決 get 和 post亂碼過濾器
 */
public class EncodingFilter implements Filter {

  public EncodingFilter() {
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // 處理請求亂碼
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletRequest myRequest = new MyRequest(httpServletRequest);

    // 處理響應亂碼
    response.setContentType("text/html;charset=utf-8");

    chain.doFilter(myRequest, response);
  }

  public void init(FilterConfig fConfig) throws ServletException {
  }

}

// 自定義request對像
class MyRequest extends HttpServletRequestWrapper {

  private HttpServletRequest request;

  private boolean hasEncode;

  public MyRequest(HttpServletRequest request) {
    super(request);// super必須寫
    this.request = request;
  }

  // 對需要增強方法 進行覆蓋
  @Override
  public Map<String, String[]> getParameterMap() {
    // 先獲得請求方式
    String method = request.getMethod();
    if (method.equalsIgnoreCase("post")) {
      // post請求
      try {
        // 處理post亂碼
        request.setCharacterEncoding("utf-8");
        return request.getParameterMap();
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    } else if (method.equalsIgnoreCase("get")) {
      // get請求
      Map<String, String[]> parameterMap = request.getParameterMap();
      if (!hasEncode) { // 確保get手動編碼邏輯只運行一次
        for (String parameterName : parameterMap.keySet()) {
          String[] values = parameterMap.get(parameterName);
          if (values != null) {
            for (int i = 0; i < values.length; i++) {
              try {
                // 處理get亂碼
                values[i] = new String(values[i]
                    .getBytes("ISO-8859-1"), "utf-8");
              } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
              }
            }
          }
        }
        hasEncode = true;
      }
      return parameterMap;
    }

    return super.getParameterMap();
  }

  @Override
  public String getParameter(String name) {
    Map<String, String[]> parameterMap = getParameterMap();
    String[] values = parameterMap.get(name);
    if (values == null) {
      return null;
    }
    return values[0]; // 取回參數的第一個值
  }

  @Override
  public String[] getParameterValues(String name) {
    Map<String, String[]> parameterMap = getParameterMap();
    String[] values = parameterMap.get(name);
    return values;
  }

}
