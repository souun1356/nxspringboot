<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
 <script type="text/javascript">
 function cancel() {
	window.location="${pageContext.request.contextPath}/ProductFindAllServlet";
}
function add() {
	var options=$("#threelevel option:selected").val();
	document.getElementById("c3code").value=options;
	document.getElementById("add").submit();
}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/ProductAddServlet"
		method="post" encType="multipart/form-data" id="add">
		<table border="1" align="center">
			<tr>
				<td>商品名稱</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>商品價格</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>商品數量</td>
				<td><input type="text" name="pnum">
				<input type="hidden" name="c3code" id="c3code"></td>
			</tr>
			<tr>
				<td>商品類別</td>
				<td>一級分類：
					<select id="onelevel" onchange="select_onelevel()">
						<option value="">-請選擇-</option>
						<c:forEach var="l" items="${onelevel}">
							<option value="${l.code}">${l.name}</option>
						</c:forEach>
					</select>
					二級分類：
					<select id="twolevel" onchange="selsct_twolevel()">
						<option value="">-請選擇-</option>
					</select>
					三級分類：
				<select id="threelevel">
					<option value="">-請選擇-</option>
				</select></td>
			</tr>
			<tr>
				<td>商品圖片</td>
				<td><input type="file" name="f"></td>
			</tr>
			<tr>
				<td>商品顏色</td>
				<td><input type="text" name="color"></td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><textarea rows="10" cols="20" name="description"></textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="button" value="添加" onclick="add()">&nbsp;&nbsp;
					<input type="reset" value="取消" onclick="cancel()">
				</td>

			</tr>
			
		</table>
</form>
<script type="text/javascript" src="CategoryJS/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="CategoryJS/onloada.js"></script>

</body>
</html>