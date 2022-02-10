<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="UTF-8">
    <title>結算頁面</title>
    <%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
    <%--<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
    <span class="wel_word">結算</span>
    <div>
        <span>歡迎,<span class="um_span">${sessionScope.user.username}</span></span>
        <a href="pages/cart/cart.jsp">購物車</a>
        <a href="orderServlet?action=list">我的訂單</a>
        <a href="userServlet?action=logout">登出</a>&nbsp;&nbsp;&nbsp;
    </div>
</div>
<div id="main">
    <h1>您的訂單已結算，訂單號為: ${sessionScope.orderId}</h1>
    <div style="text-align:center; color:red;"><h2><a href="orderServlet?action=list">查看我的訂單</a></h2></div>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>