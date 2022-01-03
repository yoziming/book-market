<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的訂單</title>
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
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的訂單</span>
    <div>
        <%--靜態包含，登入 成功之後的菜單 --%>
        <%@ include file="/pages/common/login_success_menu.jsp" %>
    </div>
</div>
<div id="main">
    <table>
        <tr>
            <td>圖書編號</td>
            <td>書名</td>
            <td>數量</td>
            <td>單價</td>
            <td>總價</td>
        </tr>
        <c:if test="${empty sessionScope.orderItems}">
            <tr>
                <td colspan="4">訂單為空!</td>
                <td><a href="">回首頁</a></td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.id}</td>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>