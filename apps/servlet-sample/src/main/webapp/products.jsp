<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Mysql category</title>
    <style>
      table {
        border-collapse: collapse;
      }

      table,
      th,
      td {
        border: 1px solid black;
      }
    </style>
  </head>
  <body>
    el: ${el}
    <br/>
    jstl: <c:out value="${jstl}"/>

    <table>
      <c:forEach items="${dataList2}" var="row">
      <tr>
        <td><c:out value="${row.id}"/></td>
        <td><c:out value="${row.name}"/></td>
        <td><c:out value="${row.price}"/></td>
        <td><c:out value="${row.pnum}"/></td>
        <td><c:out value="${row.c3code}"/></td>
        <td><c:out value="${row.imgurl}"/></td>
        <td><c:out value="${row.description}"/></td>
        <td><c:out value="${row.color}"/></td>
      </tr>
      </c:forEach>
    </table>
  </body>
</html>
