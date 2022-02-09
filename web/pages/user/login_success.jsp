<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>會員登入</title>
    <%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <div>
        <span>歡迎,<span class="um_span">${sessionScope.user.username}</span></span>
        <a href="pages/cart/cart.jsp">購物車</a>
        <a href="orderServlet?action=list">我的訂單</a>
        <a href="userServlet?action=logout">登出</a>&nbsp;&nbsp;
        <a href="index.jsp">返回首頁</a>
    </div>
</div>

<div id="main">
    <h1>歡迎回來
        <a href="index.jsp">返回首頁</a>
    </h1>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>