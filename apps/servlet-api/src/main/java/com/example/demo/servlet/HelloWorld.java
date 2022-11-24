package com.example.demo.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.dto.CommonHeaderResponse;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.dto.CustomerResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/hello")
public class HelloWorld extends HttpServlet {
  int accesses = 0;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    CustomerResponse customerResponse = new CustomerResponse();
    CustomerResponseBody responseBody = new CustomerResponseBody();

    CommonHeaderResponse header = new CommonHeaderResponse();
    header.setFunId("1");
    header.setKey("2");
    header.setRole("3");
    header.setClientIp("4");
    header.setCode("0000");
    header.setMsg("成功");

    responseBody.setCustomerId("A123456789");
    responseBody.setName("Alan");
    responseBody.setTel("0912345678");
    responseBody.setAddr("地址");
    responseBody.setAge(BigDecimal.valueOf(20));

    customerResponse.setHeader(header);
    customerResponse.setBody(Arrays.asList(responseBody));

    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), customerResponse);
  }
}
