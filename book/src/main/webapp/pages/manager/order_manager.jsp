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
    <script type="text/javascript">
        $(function () {
            // 綁定點擊事件
            $(".ChangeOrderStatus").click(function () {
                return confirm("確定該訂單已出貨?");
            });
        });
    </script>
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
            <td>訂單編號</td>
            <td>日期</td>
            <td>金額</td>
            <td>狀態</td>
            <td>操作</td>
            <td>詳情</td>
        </tr>
        <c:if test="${empty sessionScope.orders}">
            <tr>
                <td colspan="4">尚未建立訂單!</td>
                <td><a href="">回首頁</a></td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.orders}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td>
                    <c:if test="${order.status eq 0}">未發貨</c:if>
                    <c:if test="${order.status eq 1}">送貨中</c:if>
                    <c:if test="${order.status eq 2}">已完成</c:if>
                </td>
                <td>
                    <c:if test="${order.status eq 0}">
                        <a class="ChangeOrderStatus"
                           href="OrderServlet?action=sendOrder&orderId=${order.orderId}">確認發貨</a>
                    </c:if>
                </td>
                <td><a href="OrderServlet?action=showOrderDetail&orderId=${order.orderId}">查看詳情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>