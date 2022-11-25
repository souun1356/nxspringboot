<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊成功3秒後跳轉</title>
<meta http-equiv="refresh" content="3;url=http://localhost:8080/estore">
</head>
<body>
<h1>
		註冊成功，<span id="s">3</span>秒後跳轉到<a href='http://localhost:8080/estore'>首頁</a>
</h1>
<script type="text/javascript">
	var interval;
	window.onload = function() {
		interval = window.setInterval("fun()", 1000); //設置1秒調用一次fun函數
	};

	function fun() {
		var time = document.getElementById("s").innerHTML;

		//判斷如果等於0了，不在進行調用fun函數，
		if (time == 0) {
			window.clearInterval(interval);
			return;
		}

		document.getElementById("s").innerHTML = (time - 1);
	}
</script>

</body>
</html>
