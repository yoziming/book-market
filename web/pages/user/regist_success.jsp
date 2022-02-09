<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>會員註冊</title>
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
    <a href="pages/cart/cart.jsp">購物車</a>
<%--    <a href="orderServlet?action=list">我的訂單</a>--%>
<%--    <a href="userServlet?action=logout">登出</a>&nbsp;&nbsp;--%>
    <a href="index.jsp">返回首頁</a>
</div>
<div id="main">
    <h1>註冊成功! <a href="index.jsp">返回首頁</a></h1>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>