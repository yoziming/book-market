<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="UTF-8">
    <title>訂單詳情</title>
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
    <span class="wel_word">訂單詳情</span>
        <%-- 靜態包含 manager管理模塊的菜單  --%>
        <%@include file="/pages/common/manager_menu.jsp"%>
</div>
<div id="main">
    <table>
        <tr>
            <td>圖書編號</td>
            <td>書名</td>
            <td>數量</td>
            <td>單價</td>
            <td>小計</td>
        </tr>
        <c:if test="${empty sessionScope.orderItems}">
            <tr>
                <td colspan="5">查無訊息!</td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.id}</td>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.total_price}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4"></td>
            <td><a href="manager/orderServlet?action=list">回前頁</a></td>
        </tr>
    </table>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>