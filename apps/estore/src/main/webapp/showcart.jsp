<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物車</title>
	 <link rel="stylesheet" type="text/css" href="home/CSS/bootstrap.min.css">
   	 <link rel="stylesheet" type="text/css" href="home/CSS/main.css">

</head>
<body>

<script type="text/javascript">

/*1. 點擊商品名稱實現全選，全不選 		START */
function selectCk(main) {
	var flag = main.checked;

	var delCks = document.getElementsByName("delCk");

	for ( var i = 0; i < delCks.length; i++) {

		delCks[i].checked = flag;
	}
}
/* 1.點擊商品名稱實現全選，全不選 		END */



//*2.************點擊+ —  實現對商品數量的操作id是商品的id，count,是要改變的數量.數量有贈有減，重點對數據的邊界進行操作,此處應該來個666	START*****************
function changeCount(id, count, pnum) {

		//需要將count轉換成數字
		count = parseInt(count);
		//控制邊界
		if (count <= 0) {
			//刪除
			var flag = window.confirm("要刪除商品嗎?");
			if (flag) {
				count = 0;
			} else {
				count = 1;

			}
		} else if (count >= pnum) {
			alert("最大購物數量" + pnum);
			count = pnum;
		}

		location.href = "${pageContext.request.contextPath}/ChangeCountServlet?id="
				+ id + "&count=" + count;
	};

	//*2.************點擊+ —  實現對商品數量的操作id是商品的id，count,是要改變的數量.數量有贈有減，重點對數據的邊界進行操作,此處應該來個666	START*****************

  //3.鍵盤直接輸入商品數量		START.......................

	function numberText(e) {
		var keyCode;
		if (e && e.preventDefault) {
			//判斷是firefox瀏覽器
			keyCode = e.which;
		} else {
			//ie瀏覽器
			keyCode = window.event.keyCode;
		}
		//alert(keyCode);
		//0-9之間的鍵碼值是48-57
		if (!(keyCode >= 48 && keyCode <= 57 || keyCode == 8)) {
			//阻止事件的默認行為
			if (e && e.preventDefault) {
				// e對像存在，preventDefault方法存在 ---- 火狐瀏覽器
				e.preventDefault();
			} else {
				// 不支持e對象，或者沒有preventDefault方法 ---- IE
				window.event.returnValue = false;
			}

		}
	};
  //3.鍵盤直接輸入商品數量		END.......................



	//4.刪除商品---------賊J8牛逼
	function removeProduct(id) {
		var flag = window.confirm("要刪除商品碼?");

		if (flag) {
			//要刪除
			location.href = "${pageContext.request.contextPath}/RemoveProductFromCartServlet?id="
					+ id;
		}
	}




	//5.批量刪除START
	function delP() {
		var param=""; //它是用於拼接參數.
		var delCks = document.getElementsByName("delCk");
		for ( var i = 0; i < delCks.length; i++) {
			if (delCks[i].checked == true) {
				param+="id="+delCks[i].value+"&";
			}
		}

		if(param!=""){
			param=param.substring(0,param.length-1);
			location.href="${pageContext.request.contextPath}/RemoveSelectProductFromCartServlet?"+param;

		}
	};
	//5.批量刪除END


	//點擊購物車圖片跳轉到訂單頁面生成訂單
	function createOrder(){
		location.href="order.jsp";
	}
	function tocart() {
		location.href="http://localhost:8080/estore/showcart.jsp";
	}
	function phone(obj) {
		location.href="${pageContext.request.contextPath}/FindCategoryServlet?name="+encodeURIComponent(encodeURIComponent(obj));
	}


</script>

<header>
    <nav class="navbar navbar-inverse" role="navigation">
     <div class="logo"></div>
        <a href="ProductFindAllServlet">首頁</a>
        <a href="javascript:void(0)" onclick="phone('手機')">手機</a>
        <a href="javascript:void(0)" onclick="phone('平板')">平板</a>
        <a href="javascript:void(0)" onclick="phone('筆記本')">筆記本</a>
        <a href="javascript:void(0)" onclick="phone('配件')">配件</a>
        <span class="slider-bar"></span>
        <i class="carts" onclick="tocart()"></i>
        <span><c:if test="${not empty user }">
        			 <h4 class="user">${user.username}</h4>
					 <a class="logout" href="${pageContext.request.contextPath}/LoginOutServlet">註銷</a>
			</c:if>
			<c:if test="${ empty user }">
        		<h4 class="signin" data-toggle="modal" data-target="#log-wrapper">登錄</h4>
            	<h4 class="signup" data-toggle="modal" data-target="#log-wrapper">註冊</h4>
            </c:if>
		</span>
    </nav>

</header>

<!--登錄註冊-->
<div id="log-wrapper" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-content modal-dialog" id="log-move">
        <canvas id="myCanvas"></canvas>
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
        <div id="log">
            <div id="navs-slider">
                <a id="#signin" class="active">登錄</a>
                <a id="#signup">註冊</a>
                <span class="navs-slider-bar"></span>
            </div>
            <form id="sign-form-1" action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <div class="group-inputs">
                    <div class="username input-wrapper">
                        <input aria-label="用戶名/郵箱" placeholder="用戶名/郵箱" required="" type="text" value=""
                               name="username" id="signup-email_adress">
                    </div>
                    <div class="input-wrapper password">
                        <input required="" type="password" id="password-1" name="password" aria-label="密碼"
                               placeholder="密碼">
                        <span id="password_message-1"></span>
                    </div>
                    <div class="captcha input-wrapper" data-type="en">
                        <input id="captcha" name="captcha" placeholder="驗證碼" required="" data-rule-required="true"
                               data-msg-required="請填寫驗證碼">
                        <img class="captcha-img" data-toggle="tooltip" data-placement="top" title="看不清楚？換一張"
                             alt="驗證碼" src="${pageContext.request.contextPath}/checkImg" id="cimg" onclick="change()">
                    </div>
                </div>
                <div id="check-div">
                    <input type="checkbox" id="remember" name="remember" value="on"/>
                    <label for="remember"></label><em>記住用戶</em>
                    <input type="checkbox" id="autologin" name="autologin" value="on"/>
                    <label for="autologin"></label> <em>自動登陸</em>
                </div>
                <div class="sign-btn">
                    <button class="sign-button submit" type="submit">登錄</button>
                </div>
            </form>
            <form id="sign-form-2" action="${pageContext.request.contextPath}/RegistServlet" method="post">
                <div class="group-inputs">
                    <div class="username input-wrapper">
                        <input aria-label="用戶名" placeholder="用戶名" required="" type="text" value=""
                               name="username">
                        <span id="username_message"></span>
                    </div>
                    <div class="email input-wrapper">
                        <input aria-label="郵箱" placeholder="郵箱" required="" type="text" value=""
                               name="email" id="email_adress">
                        <span id="email_message"></span>
                    </div>
                    <div class="input-wrapper password">
                        <input required="" type="password" id="password" name="password" aria-label="密碼"
                               placeholder="密碼（不少於 6 位）">
                        <span id="password_message"></span>
                    </div>
                    <div class="input-wrapper password">
                        <input required="" type="password" id="repassword" name="repassword" aria-label="重複密碼"
                               placeholder="重複密碼">
                        <span id="repassword_message"></span>
                    </div>
                    <div class="captcha input-wrapper" data-type="en">
                        <input id="captcha-1" name="captcha" placeholder="驗證碼" required="" data-rule-required="true"
                               data-msg-required="請填寫驗證碼">
                        <img class="captcha-img" data-toggle="tooltip" data-placement="top" title="看不清楚？換一張"
                             alt="驗證碼" src="${pageContext.request.contextPath}/checkImg" id="cimg" onclick="change()">
                        <span id="checkcode_message"></span>
                    </div>
                </div>
                <div class="sign-btn">
                    <button class="sign-button submit" type="submit">註冊</button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
<!--登錄註冊END-->



	<c:if test="${ empty cart }">
	購物車中沒有商品
	</c:if>
 <!-- 購物車裡有數據時的操作			START -->
<c:if test="${not empty cart}">
<section class="Carts">
    <div class="head">待結算商品</div>
    <div class="title">
        <ul>
            <li>
                <div class="Checkbox">
                    <input type="checkbox" id="all-select" value="on" onclick="selectCk(this)"	/>
                    <label for="all-select"></label>
                </div>
                全選
            </li>
            <li>商品信息</li>
            <li>型號</li>
            <li>單價</li>
            <li>數量</li>
            <li>金額</li>
            <li>操作</li>
        </ul>
    </div>
    <ul class="carts-content">

   <c:set var="totalMoney" value="0" />
   <c:set var="totalCount" value="0" />

	<c:forEach items="${cart}" var="c">
      <%int num=(int)(Math.random()*10000);%>
        <ul>
            <li>
                <div class="Checkbox">
                    <input type="checkbox" id="<%=num %>"  name="delCk" value="${c.key.id}"	/>
                    <label for="<%=num %>"></label>
                </div>
            </li>
            <li>
                <img src="upload/${c.key.imgurl}">
                <div class="carts-details">${c.key.description}</div>
            </li>
            <li>
                <ul class="carts-type">
                    <li><span>顏色</span><a>${c.key.color}</a></li>
                    <li><span>內存</span><a>64G</a></li>
                    <li><span>版本</span><a>聯通4G/移動4G/電信4G</a></li>
                    <li><span>銷售地區</span><a>加拿大</a></li>
                </ul>
            </li>
            <li><span class="price">${c.key.price }</span></li>
            <li>

                <div class="count-box">
                	<!-- 減少商品數量	START -->
                    <input class="min" name="" type="button" value="-"	onclick="changeCount('${c.key.id}','${c.value-1}')"/>
                	<!-- 減少商品數量	END -->

                    <!-- 顯示商品數量		START -->
                    <input class="text-box" name="" type="text" value="${c.value}"	onblur="changeCount('${c.key.id}',this.value,'${c.key.pnum}')"
						onkeydown="numberText(event)"/>
					<!-- 顯示商品數量		END -->
                    <!-- 增加 商品數量	START-->
                    <input class="add" name="" type="button" value="+" onclick="changeCount('${c.key.id}','${c.value+1}','${c.key.pnum}')" />
                </div>

            </li>
            <li><span class="price">${totalMoney+c.key.price*c.value}</span></li>

            <li><a	href="javascript:void(0)" onclick="removeProduct('${c.key.id}')">刪除</a></li>
        </ul>

      	<c:set var="totalMoney" value="${totalMoney+c.key.price*c.value}" />
      	<c:set var="totalCount" value="${totalCount+c.value}" />

		</c:forEach>



    </ul>
    <div class="carts-foot">
        <ul>
            <li>
                <div class="Checkbox">
                    <input type="checkbox" id="all-select-1" value="on"	 href="javascript:void(0)" onclick="selectCk(this)"	/>
                    <label for="all-select-1"></label>
                </div>
                全選
            </li>
            <li><a  onclick="delP();">刪除</a></li>
            <li>&nbsp;</li>
            <li>已選商品<span class="count">${totalCount}</span>件</li>
            <li>合計：<span class="price">${totalMoney}</span></li>
            <li><a class="sum"	onclick="createOrder()">結算</a></li>
        </ul>
    </div>
</section>
 </c:if>
 <!-- 購物車裡有數據時的操作			END -->
 <aside class="aside-tool">
    <ul>
        <li class="customer">
            <a href="http://wpa.qq.com/msgrd?v=3&uin=476759153&site=qq&menu=yes" target=_blank
               clickid=guanwang_navigation_customer>聯繫客服</a>
        </li>
        <li class="top"></li>
    </ul>
</aside>
<footer>
   <div>
       <ul>
           <li>開發人員1</li>
           <li>唐宗博</li>
       </ul>
       <ul>
           <li>開發人員2</li>
           <li>辜鵬</li>
       </ul>
    </div>
</footer>
<script type="text/javascript" src="home/JS/jquery.min.js"></script>
<script type="text/javascript" src="home/JS/jquery-ui.js"></script>
<script type="text/javascript" src="home/JS/bootstrap.min.js"></script>
<script type="text/javascript" src="home/JS/bg-canvas.js"></script>
<script type="text/javascript" src="home/JS/main.js"></script>
</body>
</html>
