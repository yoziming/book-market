<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>書城首頁</title>
	<%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<Script type="text/javascript">
		$(function () {
			// 給加入購物車按鈕綁定單擊事件
			$("button.addToCart").click(function () {

				/**
				 * 在事件響應的function函數 中，有一個this物件，這個this物件，是當前正在響應事件的dom物件
				 * @type {jQuery}
				 */
				var bookId = $(this).attr("bookId");
				location.href = "${pageScope.basePath}cartServlet?action=addItem&id=" + bookId;
				// 發ajax請求，添加商品到購物車
				// $.getJSON("http://localhost:8080/bookmarket_war_exploded/cartServlet","action=ajaxAddItem&id=" + bookId,function (data) {
				// 	$("#cartTotalCount").text("您的購物車中有 " + data.totalCount + " 件商品");
				// 	$("#cartLastName").text(data.lastName);
				// })
			});
		});
	</Script>
</head>

<body>
<div id="header">
	<%--<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
	<span class="wel_word">柚子書城</span>
	<div>
		<%--如果用戶還沒有登入，顯示     【登入 和註冊的菜單】 --%>
		<c:if test="${empty sessionScope.user}">
			<a href="pages/cart/cart.jsp">購物車</a>
			<a href="pages/user/login.jsp">登入</a> |
			<a href="pages/user/regist.jsp">註冊</a> &nbsp;&nbsp;
		</c:if>
		<%--如果已經登入，則顯示 登入 成功之後的用戶信息。--%>
		<c:if test="${not empty sessionScope.user}">
			<span>歡迎,<span class="um_span">${sessionScope.user.username}</span></span>
			<a href="pages/cart/cart.jsp">購物車</a>
			<a href="orderServlet?action=list">我的訂單</a>
			<a href="userServlet?action=logout">登出</a>&nbsp;&nbsp;&nbsp;
		</c:if>
		<a href="pages/manager/manager.jsp">後台管理</a>
	</div>
</div>
<div id="main">
	<div id="book">
		<div class="book_cond">
			<form action="client/bookServlet" method="get">
				<input type="hidden" name="action" value="pageByPrice">
				價格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
				<input id="max" type="text" name="max" value="${param.max}"> 元
				<input type="submit" value="篩選" />
			</form>
		</div>
		<div style="text-align: center">
			<c:if test="${empty sessionScope.cart.items}">
				<%--購物車為空的輸出--%>
				<span id="cartTotalCount">好書優惠促銷中，買千送百！</span>
				<div>
					<span style="color: red" id="cartLastName">當前購物車為空</span>
				</div>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--購物車非空的輸出--%>
				<span id="cartTotalCount">您的購物車中有 ${sessionScope.cart.totalCount} 件商品</span>
				<div>
					您剛剛將<span style="color: red" id="cartLastName2">${sessionScope.lastName}</span>加入到了購物車中
				</div>
			</c:if>
		</div>

		<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">書名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">價格:</span>
						<span class="sp2">＄${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">銷量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">庫存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}" class="addToCart">加入購物車</button>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<%--靜態包含分頁條--%>
	<%@include file="/pages/common/page_nav.jsp"%>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>