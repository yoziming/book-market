<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="UTF-8">
    <title>後台管理</title>
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
    <span class="wel_word">後台管理系統</span>
    <%--狀態欄的三個按鍵--%>
    <div>
        			<a href="manager/bookServlet?action=list">圖書管理</a>
        			<a href="manager/orderServlet?action=list">訂單管理</a>
        <a href="index.jsp">返回書城</a>
    </div>
</div>
<div id="main">
    <h1>歡迎使用後台管理系統，請選擇服務</h1>
    <div style="text-align:center; color:red;"><h2>
        <a href="manager/bookServlet?action=list">圖書管理</a>
        <br>
        <a href="manager/orderServlet?action=list">訂單管理</a>
        <br>
    </h2></div>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>