<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>我的訂單 </title>
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
    <span class="wel_word">用戶訂單</span>
    <%--靜態包含，登入 成功之後的菜單 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>
<div id="main">
    <h1>目前尚無成立的訂單</h1>
    <div style="text-align:center; color:red;"><h2><a href="pages/cart/cart.jsp">察看購物車</a></h2></div>
    <div style="text-align:center; color:red;"><h2><a href="index.jsp">挑選更多商品</a></h2></div>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
