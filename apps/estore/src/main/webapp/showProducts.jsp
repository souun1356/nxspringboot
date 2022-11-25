<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function del(id) {

		var flag = window.confirm("確認刪除嗎");

		if (flag) {
			//確認刪除
			location.href = "${pageContext.request.contextPath}/ProductDelByIdServlet?id="
					+ id;
		}
	};

	function change() {
		//1.得到id為main的這個checkbox
		var main = document.getElementById("main");

		var flag = main.checked;

		//2.得到所有name=ck的checkbox
		var cks = document.getElementsByName("ck");

		//3.將cks中所有的checkbox的checked值設置為flag
		for ( var i = 0; i < cks.length; i++) {
			cks[i].checked = flag;
		}
	};
	
	function sendDel(){
		var flag = window.confirm("確認刪除嗎");

		if (flag) {
			//確認刪除
			document.getElementById("f").submit();//表單提交
		}
		
		var cks = document.getElementsByName("ck");
		
	};
	
	function sel() {
		var msg = document.getElementById("msg").value;
		if(msg==null||msg=="")
			alert("請輸入你要查詢的內容！！！");
		document.getElementById("s").submit();
		
	}
</script>
</head>

<body>

	<c:if test="${empty pro}">
		無商品信息<br>
		<a href="${pageContext.request.contextPath}/CategoryServlet">添加</a>
	</c:if>


	<c:if test="${not empty pro}">
		<div align="center">
			<form action="${pageContext.request.contextPath}/ProductSimpleServlet" method="post" id="s">
			<select name="field">
				<option disabled="disabled">請選擇條件</option>
				<option value="name">按商品名稱查詢</option>
				<option value="description">按商品描述查詢</option>
			</select>
			<input type="text" name="msg" id="msg">
			<input type="button" value="查詢" onclick="sel()">
			</form>
		</div>
		<br>
		<form action="${pageContext.request.contextPath}/ProductDelSelectServlet" method="post" id="f">
			<table border="1" align="center" width="85%">
				<tr>
					<td><input type="checkbox" id="main" onclick="change();">
					</td>
					<td>商品編號</td>
					<td>商品名稱</td>
					<td>商品價格</td>
					<td>商品數量</td>
					<td>商品分類</td>
					<td>商品顏色</td>
					<td>商品圖片</td>
					<td>商品描述</td>
					<td>操作</td>
				</tr>

				<c:forEach items="${pro}" var="p">
					<tr>
						<td><input type="checkbox" value="${p.id}" name="ck"></td>
						<td>${p.id }</td>
						<td>${p.name}</td>
						<td>${p.price }</td>
						<td>${p.pnum }</td>
						<td>${p.c3code }</td>
						<td>${p.color }</td>
						<td height="160" width="300"><img alt="圖片不存在或已刪除" src="/upload/${p.imgurl}" width="300" height="160"></td>
						<td>${p.description }</td>
						<td><a
							href="${pageContext.request.contextPath}/ProductFindByIdServlet?id=${p.id}">編輯</a>

							&nbsp;<a href="javascript:void(0)" onclick="del('${p.id}')">刪除</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="10"><a href="javascript:void(0)" onclick="sendDel();">刪除選中</a></td>
					<td><a href="${pageContext.request.contextPath}/CategoryServlet">添加</a>
					</td>
				</tr>
			</table>
		</form>
	</c:if>

</body>
</html>